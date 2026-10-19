package tech.eta.dataloader_d.user.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import org.jspecify.annotations.NonNull;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Collections;
import java.util.List;

/**
 * It uses a propper user role enum and supports being read from a JSON with a numeric representation of the roles too.
 * TODO: Class should probably be renamed to UserInfo, UserData or similar. UserConfig implies actual configuration...
 */
public record UserConfig(
        @NonNull String username,
        @NonNull String password,
        @NonNull List<UserRole> roles
) {
    // extra constructor to support creation with a numeric roles set
    public UserConfig(@NonNull String name, @NonNull String password, int roles) {
        this(name, password, UserRole.numericToRoles(roles));
    }

    @JsonCreator
    public static UserConfig create(
            @JsonProperty("username") @JsonAlias("name") String username,
            @JsonProperty("password") String password,
            @JsonProperty("roles") @JsonAlias({"user_roles", "userroles"}) Object roles
    ) {
        // here we could/should use the UserRole item count, as the list may never contain more than those roles...
        List<UserRole> parsedRoles = switch (roles) {
            // if JSON provides a Number / bitflag  e.g. 5
            case Number number -> UserRole.numericToRoles(number.intValue());
            // JSON provides list of strings e.g. ["USER", "ADMIN"]
            case List<?> list -> UserRole.fromStrings(list.stream().map(Object::toString).toList());
            //case List<?> list -> list.stream()
            //        .map(obj -> UserRole.valueOf(obj.toString().strip().toUpperCase()))
            //        .map(obj -> UserRole.valueOf(obj.toString().strip().toUpperCase()))
            //        .toList();
            default -> Collections.emptyList();
        };

        // we support two cases: if the roles is a number we convert it to a list, else we normalize the list of roles
        return new UserConfig(username, password, parsedRoles);
    }

    // By default, serialization uses the format of roles (a list of enums). With this we can explicitly set the format.
    @JsonGetter("roles")
    public List<UserRole> getRoles() { return roles; }
}
