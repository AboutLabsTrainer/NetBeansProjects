package tech.eta.dataloader_d.user.repositories;

import org.jspecify.annotations.NonNull;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tech.eta.dataloader_d.user.models.UserDB;
import tech.eta.dataloader_d.user.models.UserConfig;

import java.util.Optional;

@Repository
@ConditionalOnProperty(name = "app.auth.mode", havingValue = "database", matchIfMissing = true)
public class UserDaoDB implements UserDao {

    private final UserDBRepository userDBRepository; // Das echte JPA-Repository

    public UserDaoDB(UserDBRepository userDBRepository) {
        this.userDBRepository = userDBRepository;
    }

    @Override
    @Transactional(readOnly = true) // readOnly causes errors?
    public Optional<UserConfig> findByUsername(@NonNull String username) {
        return userDBRepository.findByUsername(username).map(UserDaoDB::userToConfig);
    }

    @Override
    @Transactional
    public void saveUser(@NonNull UserConfig userConfig) { userDBRepository.save(configToUser(userConfig)); }


    private static @NonNull String normalizeRole(@NonNull String role) {
        String s = role.toUpperCase().strip();
        return s.startsWith("ROLE_")?s.substring(5):s;
    }

    public static @NonNull UserConfig userToConfig(@NonNull UserDB user) {
        return new UserConfig(user.getUsername(), user.getPassword(), user.getRoles());
    }

    public static @NonNull UserDB configToUser(@NonNull UserConfig userConfig) {
        UserDB user = new UserDB();
        user.setUsername(userConfig.username());
        user.setPassword(userConfig.password()); // Passwort ist bereits verschlüsselt
        user.getRoles().addAll(userConfig.roles());
        return user;
    }
}
