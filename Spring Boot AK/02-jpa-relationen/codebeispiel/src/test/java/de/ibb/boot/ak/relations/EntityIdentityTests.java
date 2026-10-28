package de.ibb.boot.ak.relations;

import de.ibb.boot.ak.relations.entity.Address;
import de.ibb.boot.ak.relations.entity.AddressType;
import de.ibb.boot.ak.relations.entity.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * equals und hashCode der Entities müssen in einem HashSet über persist hinweg stabil bleiben.
 * Läuft mit dem Profil h2 auf einer In-Memory-Datenbank, nicht gegen MySQL.
 */
@DataJpaTest
@ActiveProfiles("h2")
class EntityIdentityTests {

    @Autowired
    private TestEntityManager em;

    @Test
    void addressStaysInSetAfterPersist() {
        Person person = newPerson();
        Address address = newAddress("Hauptstraße 1");
        person.addAddress(address);

        em.persistAndFlush(person);
        assertThat(address.getId()).isNotNull();

        assertThat(person.getAddresses()).contains(address);
    }

    @Test
    void removeAddressAfterPersistDeletesOrphan() {
        Person person = newPerson();
        Address address = newAddress("Hauptstraße 1");
        person.addAddress(address);
        em.persistAndFlush(person);
        Long addressId = address.getId();

        person.removeAddress(address);
        em.flush();
        em.clear();

        assertThat(em.find(Address.class, addressId)).isNull();
    }

    @Test
    void twoNewAddressesWithSameDataAreBothKept() {
        Person person = newPerson();
        person.addAddress(newAddress("Hauptstraße 1"));
        person.addAddress(newAddress("Hauptstraße 1"));

        assertThat(person.getAddresses()).hasSize(2);
    }

    @Test
    void changingAFieldKeepsTheAddressFindable() {
        Set<Address> set = new HashSet<>();
        Address address = newAddress("Hauptstraße 1");
        set.add(address);

        address.setStreet("Nebenstraße 2");

        assertThat(set.contains(address)).isTrue();
    }

    private static Person newPerson() {
        Person person = new Person();
        person.setFirstname("Ada");
        person.setLastname("Lovelace");
        person.setEmail("ada@example.com");
        return person;
    }

    private static Address newAddress(String street) {
        Address address = new Address();
        address.setStreet(street);
        address.setCity("Berlin");
        address.setZipCode("10115");
        address.setType(AddressType.values()[0]);
        return address;
    }
}
