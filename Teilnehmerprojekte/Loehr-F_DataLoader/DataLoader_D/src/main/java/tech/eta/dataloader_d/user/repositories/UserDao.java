package tech.eta.dataloader_d.user.repositories;

import tech.eta.dataloader_d.user.models.UserConfig;

import java.util.Optional;

public interface UserDao {
    Optional<UserConfig> findByUsername(String username);
    void saveUser(UserConfig userConfig); // Benutzer anlegen...
}
