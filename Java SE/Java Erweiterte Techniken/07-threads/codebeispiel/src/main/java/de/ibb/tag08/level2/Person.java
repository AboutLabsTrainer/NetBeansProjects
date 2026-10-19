package de.ibb.tag08.level2;

/**
 * Beispiel-Domainklasse für Validierung mit {@link Validate}.
 */
public class Person {

    @Validate(notEmpty = true, message = "Name ist erforderlich")
    private String name;

    @Validate(min = 0, max = 150, message = "Alter außerhalb des gültigen Bereichs")
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}

