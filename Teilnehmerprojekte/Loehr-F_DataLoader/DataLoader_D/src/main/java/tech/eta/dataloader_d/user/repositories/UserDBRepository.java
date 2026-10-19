package tech.eta.dataloader_d.user.repositories;

import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import tech.eta.dataloader_d.user.models.UserDB;

import java.util.Optional;

// used solely by the UserDaoDB ... should this be a private / package-private interface?
public interface UserDBRepository extends JpaRepository<UserDB, Long> {
    Optional<UserDB> findByUsername(@NonNull String username);
}
