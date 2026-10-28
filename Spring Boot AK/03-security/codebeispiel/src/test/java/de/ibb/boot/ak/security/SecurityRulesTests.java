package de.ibb.boot.ak.security;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.ibb.boot.ak.security.entity.Person;
import de.ibb.boot.ak.security.repository.PersonRepository;
import de.ibb.boot.ak.security.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.net.http.HttpClient;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Prüft die Regeln der Filterketten und der Methodensicherheit über echtes HTTP.
 * Die Konten admin und user legt der DataLoader an. user hat kein DELETE_PRIVILEGES.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class SecurityRulesTests {

    @Autowired
    private TestRestTemplate rest;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private Person userPerson;
    private Person adminSecret;

    @BeforeEach
    void setUp() {
        // Redirects nicht folgen, damit der Test den 302 des Logins selbst sieht.
        rest.getRestTemplate().setRequestFactory(new JdkClientHttpRequestFactory(
                HttpClient.newBuilder().followRedirects(HttpClient.Redirect.NEVER).build()));

        userPerson = savePerson("Ute", "User", "user", false);
        adminSecret = savePerson("Arne", "Admin", "admin", true);
    }

    @AfterEach
    void tearDown() {
        personRepository.deleteAll();
    }

    @Test
    void deleteNeedsDeletePrivileges() {
        ResponseEntity<String> asUser = call(HttpMethod.DELETE, "/api/persons/" + userPerson.getId(), token("user", "user123"), null);
        assertThat(asUser.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
        assertThat(personRepository.existsById(userPerson.getId())).isTrue();

        ResponseEntity<String> asAdmin = call(HttpMethod.DELETE, "/api/persons/" + userPerson.getId(), token("admin", "admin123"), null);
        assertThat(asAdmin.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        assertThat(personRepository.existsById(userPerson.getId())).isFalse();
    }

    @Test
    void refreshTokenIsNotAnAccessToken() throws Exception {
        JsonNode tokens = login("user", "user123");
        String refreshToken = tokens.get("refreshToken").asText();

        ResponseEntity<String> response = call(HttpMethod.GET, "/api/users/me", refreshToken, null);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
    }

    @Test
    void accessTokenCannotRefresh() throws Exception {
        JsonNode tokens = login("user", "user123");

        ResponseEntity<String> withAccess = call(HttpMethod.POST, "/api/auth/refresh", null,
                Map.of("refreshToken", tokens.get("accessToken").asText()));
        assertThat(withAccess.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);

        ResponseEntity<String> withRefresh = call(HttpMethod.POST, "/api/auth/refresh", null,
                Map.of("refreshToken", tokens.get("refreshToken").asText()));
        assertThat(withRefresh.getStatusCode()).isEqualTo(HttpStatus.OK);
        String newAccess = objectMapper.readTree(withRefresh.getBody()).get("accessToken").asText();
        assertThat(call(HttpMethod.GET, "/api/users/me", newAccess, null).getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void currentUserJsonHasNoPassword() throws Exception {
        ResponseEntity<String> response = call(HttpMethod.GET, "/api/users/me/full", token("user", "user123"), null);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        JsonNode json = objectMapper.readTree(response.getBody());
        assertThat(json.get("username").asText()).isEqualTo("user");
        assertThat(json.has("password")).isFalse();
    }

    @Test
    void confidentialPersonIsHiddenFromOtherUsers() throws Exception {
        String userToken = token("user", "user123");

        assertThat(personIds(call(HttpMethod.GET, "/api/persons", userToken, null)))
                .contains(userPerson.getId())
                .doesNotContain(adminSecret.getId());
        assertThat(call(HttpMethod.GET, "/api/persons/" + adminSecret.getId(), userToken, null).getStatusCode())
                .isEqualTo(HttpStatus.FORBIDDEN);

        assertThat(personIds(call(HttpMethod.GET, "/api/persons", token("admin", "admin123"), null)))
                .contains(userPerson.getId(), adminSecret.getId());
    }

    @Test
    void createValidatesBodyAndIgnoresClientId() throws Exception {
        String userToken = token("user", "user123");

        ResponseEntity<String> blank = call(HttpMethod.POST, "/api/persons/addPerson", userToken,
                Map.of("firstname", "", "lastname", "Leer"));
        assertThat(blank.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

        ResponseEntity<String> hijack = call(HttpMethod.POST, "/api/persons/addPerson", userToken,
                Map.of("id", adminSecret.getId(), "firstname", "Neu", "lastname", "Person"));
        assertThat(hijack.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(objectMapper.readTree(hijack.getBody()).get("id").asLong()).isNotEqualTo(adminSecret.getId());
        Person untouched = personRepository.findById(adminSecret.getId()).orElseThrow();
        assertThat(untouched.getFirstname()).isEqualTo("Arne");
        assertThat(untouched.getOwner().getUsername()).isEqualTo("admin");
    }

    @Test
    void missingPersonIsNotFound() {
        ResponseEntity<String> response = call(HttpMethod.GET, "/api/persons/999999", token("admin", "admin123"), null);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void sessionCookieDoesNotAuthenticateApi() {
        ResponseEntity<String> loginPage = rest.getForEntity("/login", String.class);
        String anonymousSession = sessionCookie(loginPage);
        Matcher csrf = Pattern.compile("name=\"_csrf\"[^>]*value=\"([^\"]+)\"").matcher(loginPage.getBody());
        assertThat(csrf.find()).isTrue();

        HttpHeaders formHeaders = new HttpHeaders();
        formHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        formHeaders.add(HttpHeaders.COOKIE, anonymousSession);
        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
        form.add("username", "user");
        form.add("password", "user123");
        form.add("_csrf", csrf.group(1));
        ResponseEntity<String> loggedIn = rest.postForEntity("/login", new HttpEntity<>(form, formHeaders), String.class);

        assertThat(loggedIn.getStatusCode()).isEqualTo(HttpStatus.FOUND);
        assertThat(loggedIn.getHeaders().getLocation()).hasPath("/persons");
        String session = sessionCookie(loggedIn);

        HttpHeaders cookieOnly = new HttpHeaders();
        cookieOnly.add(HttpHeaders.COOKIE, session);
        assertThat(rest.exchange("/persons", HttpMethod.GET, new HttpEntity<>(cookieOnly), String.class).getStatusCode())
                .isEqualTo(HttpStatus.OK);
        assertThat(rest.exchange("/api/users/me", HttpMethod.GET, new HttpEntity<>(cookieOnly), String.class).getStatusCode())
                .isEqualTo(HttpStatus.UNAUTHORIZED);
    }

    private Person savePerson(String firstname, String lastname, String owner, boolean confidential) {
        Person person = new Person();
        person.setFirstname(firstname);
        person.setLastname(lastname);
        person.setEmail(firstname.toLowerCase() + "@example.com");
        person.setOwner(userRepository.findByUsername(owner).orElseThrow());
        person.setConfidential(confidential);
        return personRepository.save(person);
    }

    private JsonNode login(String username, String password) throws Exception {
        ResponseEntity<String> response = call(HttpMethod.POST, "/api/auth/login", null,
                Map.of("username", username, "password", password));
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        return objectMapper.readTree(response.getBody());
    }

    private String token(String username, String password) {
        try {
            return login(username, password).get("accessToken").asText();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    private ResponseEntity<String> call(HttpMethod method, String path, String bearer, Object body) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        if (bearer != null) {
            headers.setBearerAuth(bearer);
        }
        return rest.exchange(path, method, new HttpEntity<>(body, headers), String.class);
    }

    private List<Long> personIds(ResponseEntity<String> response) throws Exception {
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        List<Long> ids = new ArrayList<>();
        objectMapper.readTree(response.getBody()).forEach(p -> ids.add(p.get("id").asLong()));
        return ids;
    }

    private static String sessionCookie(ResponseEntity<?> response) {
        List<String> cookies = response.getHeaders().getOrEmpty(HttpHeaders.SET_COOKIE);
        return cookies.stream()
                .filter(c -> c.startsWith("JSESSIONID="))
                .map(c -> c.substring(0, c.indexOf(';')))
                .findFirst()
                .orElseThrow(() -> new AssertionError("Kein JSESSIONID-Cookie in " + cookies));
    }
}
