package tech.eta.dataloader_d.user.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.jspecify.annotations.NonNull;

import java.util.ArrayList;
import java.util.List;

// TODO UserDB.roles should be a HashSet, same in the UserConfig.
//      How will that look in the JSON, if we ever stored that?
//      UserConfig and UserDB should be renamed, maybe to UserInfo and UserInfoDB
@Entity
@Table(name = "users")
@Getter
public class UserDB {
    @Id // IDENTITY doesn't allow batch insertions, SEQUENCE does
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GenIDs_eta_dataloader_UserDB")
    //@SequenceGenerator(
    //        name="GenIDs_eta_dataloader_UserDB",
    //        sequenceName = "IDs_eta_dataloader_UserDB",
    //        allocationSize = 100
    //)
    @Column(unique = true, nullable = false)
    private Long id;

    @Setter
    @Column(unique = true, nullable = false)
    private @NonNull String username;

    @Setter
    @Column(nullable = false)
    private @NonNull String password;

    @ElementCollection(fetch = FetchType.EAGER)   // This should be all necessary to put roles
    @CollectionTable(name = "user_roles")         // into a separate table
    // Note: The "data.sql" had to be updated. The column "roles" was removed from table "users". Instead, the
    //      new table "user_roles" maps the column "userdb_ID" (user ids) to "roles" holding individual roles.
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private final List<UserRole> roles = new ArrayList<>(); // dadurch kann Hibernate mir die direkt anlegen
//    private final Set<UserRole> roles = new HashSet<>();
}