package com.fleetdata.personmanagement.specification;

import com.fleetdata.personmanagement.entity.Address;
import com.fleetdata.personmanagement.entity.Person;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

/**
 * Baut wiederverwendbare Criteria-Specifications für die Personensuche.
 * Der Controller kombiniert sie zur Laufzeit. Leere Kriterien liefern kein Prädikat und grenzen die Abfrage nicht ein.
 */
public final class PersonSpecifications {

    private PersonSpecifications() {
    }

    public static Specification<Person> hasLastname(String lastname) {
        return (root, query, cb) ->
                lastname == null || lastname.isBlank()
                        ? null
                        : cb.equal(root.get("lastname"), lastname);
    }

    public static Specification<Person> hasAddressInCity(String city) {
        return (root, query, cb) -> {
            if (city == null || city.isBlank()) {
                return null;
            }
            Join<Person, Address> addresses = root.join("addresses");
            query.distinct(true);
            return cb.equal(addresses.get("city"), city);
        };
    }
}
