package tech.eta.dataloader_d.user.models;

import lombok.Getter;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * An enum for user roles with utils to convert between a list of roles and its numeric representation.
 */
@Getter
public enum UserRole {
    DEVELOPER(1),
    ADMIN(2),
    MANAGER(3),
    POWERUSER(4),
    USER(5);
    private final int value;

    UserRole(int value) {
        this.value = 1<<(value-1);
    }

    // In case someone mixed up casing...
    public static @NonNull List<UserRole> fromStrings(@NonNull List<String> roles) {
        List<UserRole> list = new ArrayList<>();
        for (String role : roles) {
            for (UserRole userRole : UserRole.values()) {
                if (userRole.name().equalsIgnoreCase(role.strip())) {list.add(userRole);}
            }
        }
        return list;
    }
    
    // changing from and to a numeric representation of the user roles
    public static @NonNull List<UserRole> numericToRoles(int value) {
        List<UserRole> list = new ArrayList<>(UserRole.values().length);
        for (UserRole role : values()) {
            if ((value & role.getValue()) == role.getValue()) { list.add(role); }
        }
        return list;
    }
    public static int numericFromRoles(@NonNull List<UserRole> roles) {
        int result = 0;
        for (UserRole role : roles) result |= role.getValue();
        return result;
    }

    public static @NonNull List<String> toStrings(@NonNull List<UserRole> roles) {
        return roles.stream().map(Enum::name).toList();
    }
    public static @NonNull List<UserRole> fromUserDetails(@NonNull UserDetails userDetails) {
        final String ROLE_PREFIX = "ROLE_";
        return UserRole.fromStrings(
                userDetails.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .filter(Objects::nonNull)
                        .filter(authorityString -> authorityString.toUpperCase().startsWith(ROLE_PREFIX))
                        .map(authorityString -> authorityString.substring(ROLE_PREFIX.length()))
                        .toList()
        );
    }


}
