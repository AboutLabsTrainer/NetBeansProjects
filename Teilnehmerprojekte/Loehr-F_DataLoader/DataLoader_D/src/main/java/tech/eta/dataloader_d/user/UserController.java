package tech.eta.dataloader_d.user;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jspecify.annotations.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;
import tech.eta.dataloader_d.user.models.UserConfig;

import java.util.Map;

/**
 * used for showing the current user (for me for debugging)
 * and for access validation in the script app.js
 */
@RestController
public class UserController {

    final private AppUserDetailsService appUserDetailsService;
    private static final Logger log = LogManager.getLogger();

    public UserController(AppUserDetailsService appUserDetailsService) {
        this.appUserDetailsService = appUserDetailsService;
    }

    @GetMapping("/api/user/current")
    public Map<String, Object> currentUser(Authentication authentication) {

        if (authentication == null || !authentication.isAuthenticated() || "anonymousUser".equals(authentication.getPrincipal()))
            return Map.of("authenticated", false, "admin", false);

        boolean admin = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).anyMatch("ROLE_ADMIN"::equals);

        return Map.of("authenticated", true, "admin", admin, "username", authentication.getName());
    }

//    @PostMapping("/api/user/create")
//    @PreAuthorize("hasRole('ADMIN')")
//    public String createUser(@ModelAttribute UserConfig userConfig, Model model) {
//        model.addAttribute("userConfig", userConfig);
//        this.appUserDetailsService.registerNewUser(userConfig);
//        return "redirect:/";
//    }
    @PostMapping("/api/user/create")
    @PreAuthorize("hasRole('ADMIN')")
    public void createUser(@NonNull @RequestBody UserConfig userConfig) {
        System.out.println("ADDING USER");
//        log.info("Creating user {}", userConfig.username());
        try {
            this.appUserDetailsService.registerNewUser(userConfig);
        } catch (IllegalArgumentException e) {
            throw new ErrorResponseException(HttpStatus.NOT_ACCEPTABLE, e);// new RuntimeException("passwordEncoder"));
        } catch (ArithmeticException e) {
            throw new ErrorResponseException(HttpStatus.CONFLICT, e);// new RuntimeException("Duplicate"));
        } catch (Exception e) {
            throw new ErrorResponseException(HttpStatus.INTERNAL_SERVER_ERROR, e);
        }
    }
}
