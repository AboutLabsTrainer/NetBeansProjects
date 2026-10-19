package tech.eta.dataloader_d.user.repositories;

import org.jspecify.annotations.NonNull;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;
import tech.eta.dataloader_d.user.models.UserConfig;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Repository
// Wird NUR geladen, wenn app.auth.mode=json konfiguriert ist
@ConditionalOnProperty(name = "app.auth.mode", havingValue = "json")
public class UserDaoJson implements UserDao {
    private final List<UserConfig> users;

    public UserDaoJson(ObjectMapper objectMapper) throws IOException {
        var resource = new ClassPathResource("users.json");

        this.users = objectMapper.readValue(resource.getInputStream(), new TypeReference<>() {});
    }

    @Override
    public Optional<UserConfig> findByUsername(@NonNull String username) {
        return users.stream().filter(user -> user.username().equals(username)).findFirst();
    }

    @Override
    public void saveUser(@NonNull UserConfig userConfig) {
        // No-Op: Im JSON-Modus wird das Speichern vorerst ignoriert => Logging?
    }
}
