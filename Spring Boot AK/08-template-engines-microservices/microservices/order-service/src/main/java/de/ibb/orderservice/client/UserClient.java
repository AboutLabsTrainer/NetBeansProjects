package de.ibb.orderservice.client;
import de.ibb.orderservice.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
/**
 * Deklarativer HTTP-Client auf den User-Service.
 * Feign und die Discovery lösen den Namen user-service zur laufenden Instanz auf.
 */
@FeignClient(name = "user-service")
public interface UserClient {
    @GetMapping("/api/users/{id}")
    User getUserById(@PathVariable Long id);
}
