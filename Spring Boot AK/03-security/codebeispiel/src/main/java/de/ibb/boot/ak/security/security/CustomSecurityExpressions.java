package de.ibb.boot.ak.security.security;

import de.ibb.boot.ak.security.entity.Person;
import de.ibb.boot.ak.security.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Eigene Ausdrücke für die Methodensicherheit, erreichbar als Bean securityExpressions.
 * isOwner prüft über den SecurityContext, ob der angemeldete Benutzer den Datensatz besitzt.
 */
@Component("securityExpressions")
@RequiredArgsConstructor
public class CustomSecurityExpressions {

    private final PersonRepository personRepository;

    public boolean isOwner(Long personId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = auth.getName();

        Person person = personRepository.findById(personId).orElse(null);
        return person != null && isOwnedBy(person, currentUsername);
    }

    public boolean isOwnerOrAdmin(Long personId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean isAdmin = auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        if (isAdmin) {
            return true;
        }
        return isOwner(personId);
    }

    public boolean canView(Person person) {
        if (person == null) {
            return false;
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean isAdmin = auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        if (isAdmin) {
            return true;
        }
        if (person.isConfidential()) {
            return isOwnedBy(person, auth.getName());
        }
        return true;
    }

    private boolean isOwnedBy(Person person, String username) {
        return person.getOwner() != null && person.getOwner().getUsername().equals(username);
    }
}
