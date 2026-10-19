package tech.eta.dataloader_d.user;

import org.jspecify.annotations.NonNull;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tech.eta.dataloader_d.user.models.UserConfig;
import tech.eta.dataloader_d.user.models.UserRole;
import tech.eta.dataloader_d.user.repositories.UserDao;

import java.lang.reflect.Field;
import java.util.*;


// NOTE We want the ability to switch between both a json-based and a database-based user,
//      based on an option in the application.properties file.
//  =>  extracted handling the userConfig into conditionally defined UserDao classes.
// TODO: Rename to UserService? that would match the schema... but skip the info that it's about userDetails
@Service
public class AppUserDetailsService implements UserDetailsService {

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;
    private final Set<String> supportedEncoderIDs;

    public AppUserDetailsService(UserDao UserDao, PasswordEncoder passwordEncoder) {
        this.userDao = UserDao;
        this.passwordEncoder = passwordEncoder;
        this.supportedEncoderIDs = extractSupportedEncoderIDs(passwordEncoder);
    }

    private static Set<String> extractSupportedEncoderIDs(PasswordEncoder passwordEncoder) {
        // uses reflection, but is certain to work...
        // we could also have our encoder have explicit creation (caching the idma there) or set a defaultEncoder
        // using setDefaultPasswordEncoderForMatches which saves stuff when we use encoder.matches(...)
        if (passwordEncoder instanceof DelegatingPasswordEncoder) {
            try {
                Field field = DelegatingPasswordEncoder.class.getDeclaredField("idToPasswordEncoder");
                field.setAccessible(true);
                return ((Map<String, PasswordEncoder>) field.get(passwordEncoder)).keySet();
            } catch (NoSuchFieldException | IllegalAccessException e) {
                // Fallback auf Standard-IDs, falls sich die Spring-Security-Internas im Update ändern. Besser nicht?
                return Set.of("bcrypt", "argon2", "pbkdf2", "scrypt", "noop");
            }
        }
        return Set.of();
    }

    public static @NonNull UserDetails configToDetails(@NonNull UserConfig user) {
        return User.withUsername(user.username())
                .password(user.password())
                // .authorities(roles) // we must add "ROLE_" if using authorities for roles
                // WHY does java need this weird cast to pass a list into a vararg?
                // https://stackoverflow.com/questions/9863742/how-to-pass-an-arraylist-to-a-varargs-method-parameter
                .roles(UserRole.toStrings(user.roles()).toArray(new String[0])) // .roles does the "ROLE_"+... by itself
                .build();
    }

    public static @NonNull UserConfig detailsToConfig(@NonNull UserDetails user) {
        return new UserConfig(
                user.getUsername(),
                Objects.requireNonNull(user.getPassword()),
                UserRole.fromUserDetails(user)
        );
    }

    @Override
    public @NonNull UserDetails loadUserByUsername(@NonNull String username) throws UsernameNotFoundException {
        return userDao.findByUsername(username)
                .map(AppUserDetailsService::configToDetails)
                .orElseThrow(() -> new UsernameNotFoundException("DB: UserDB not found: " + username));
    }


    // Service-Method for the Controller to register the new user
    // Note: maybe we could change it to update as well...
    public synchronized void registerNewUser(@NonNull UserConfig user) {
        String username = user.username();
        if (userDao.findByUsername(username).isPresent()) {
            throw new ArithmeticException("DB: UserDB already exists: " + username);
        };

        // Passwort verschlüsseln // todo: check if encoded (don't encode twice) else bcrypt it
        // There's a static list of ids now... so compare with those.
        String userPassword = user.password();
        
//        userPassword.charAt(0);
        char[] c = user.password().toCharArray();
        passwordEncoder.encode(userPassword);
        boolean braced = false; // are we braced? If so we may have a id
        int i = 1; // where to find the substring?
        int end = userPassword.indexOf('}');
        if (end > 1 && userPassword.charAt(0)=='{') { // possible ID
            String encID = userPassword.substring(1, end);
            // this checks only if the encoder is known, not if the pattern is valid.
            if (!supportedEncoderIDs.contains(encID) && !encID.contains("{")) {
//                throw new IllegalArgumentException("pre-encoded password with unrecognized encoder '" + encID + "'. Cannot proceed.");
            }
            // to check if valid, we match against our self. This may throw an error if the pattern is wrong
//            passwordEncoder.matches(userPassword, userPassword); mabe??
            passwordEncoder.upgradeEncoding(userPassword); // throws illegalArgumentException
        } else userPassword = passwordEncoder.encode(userPassword);

//        String encryptedPassword = passwordEncoder.encode(user.password());
//
//        passwordEncoder.upgradeEncoding();
//        passwordEncoder.matches();
//        passwordEncoder

        List<UserRole> roles = user.roles();
        if (roles.isEmpty())
            roles.add(UserRole.USER); // TODO: check for endpoint & syncronized (ich schreibe ja in die DB => nicht multithreading-tauglich)

        // Als DTO an das aktive DAO übergeben
        // wenn existent, welchen fehler wirft es? JdbcSQLIntegrityConstraintViolationException
        userDao.saveUser(new UserConfig(user.username(), userPassword, roles));
    }
}
