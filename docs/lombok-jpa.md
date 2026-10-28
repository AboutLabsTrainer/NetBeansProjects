# Lombok und JPA

Lombok spart Getter, Setter und Konstruktoren. Bei Entities erzeugen zwei Annotationen aber Fehler, die erst zur Laufzeit auffallen. Diese Regeln gelten für alle Labs mit `@Entity`.

## Regeln

| Annotation | Auf einer Entity | Grund |
|------------|------------------|-------|
| `@Getter`, `@Setter`, `@NoArgsConstructor` | ja | JPA braucht den leeren Konstruktor, sonst nichts Besonderes |
| `@Data` | nur zusammen mit eigenem `equals`/`hashCode` | `@Data` erzeugt `equals`/`hashCode` über alle Felder. Lombok lässt beides weg, sobald die Klasse die Methoden selbst schreibt |
| `@EqualsAndHashCode` | nein | Hasht veränderliche Felder und die `id`, siehe unten |
| `@ToString` | nur mit `@ToString.Exclude` an jeder Beziehung und am Passwort | Sonst lädt `toString` lazy Beziehungen nach, läuft bei bidirektionalen Beziehungen endlos oder schreibt den Hash ins Log |
| `@AllArgsConstructor` | vermeiden | Setzt auch `id` und Beziehungen und umgeht die Hilfsmethoden wie `addAddress` |

## Warum `@EqualsAndHashCode` falsch ist

Ein `HashSet` legt ein Element in den Eimer seines `hashCode` zum Zeitpunkt von `add`. Ändert sich der Hash danach, sucht `contains` und `remove` im falschen Eimer.

1. `person.addAddress(address)`: `id` ist null, der Hash wird aus den Feldern mit `id = null` berechnet.
2. `persist`: Die Datenbank vergibt die `id`. Der Hash ändert sich.
3. `person.removeAddress(address)`: `remove` findet die Adresse nicht. Mit `orphanRemoval = true` löscht Hibernate deshalb nichts, und es gibt keinen Fehler.

Dasselbe passiert, wenn ein Feld wie `street` geändert wird. Zwei neue Adressen mit denselben Daten gelten außerdem als gleich, das `Set` behält nur eine.

## Muster für `equals` und `hashCode`

```java
@Override
public boolean equals(Object o) {
    if (this == o) {
        return true;
    }
    if (!(o instanceof Address other)) {
        return false;
    }
    return id != null && id.equals(other.getId());
}

@Override
public int hashCode() {
    return Address.class.hashCode();
}
```

- Gleich ist nur dieselbe Datenbankzeile. Ein neues Objekt ohne `id` ist nur sich selbst gleich.
- `hashCode` ist pro Klasse konstant und ändert sich nie. Alle Elemente landen im selben Eimer. Für die Mengen einer Beziehung ist das schnell genug.
- `other.getId()` statt `other.id`: Bei einem Hibernate-Proxy ist das Feld leer, der Getter lädt den Wert.
- `instanceof` statt `getClass()`: Ein Proxy ist eine Unterklasse und hätte eine andere Klasse.

Das Muster steht in `Spring Boot AK/02-jpa-relationen/codebeispiel` (`Person`, `Address`, `Order`) und in `Spring Boot AK/03-security/codebeispiel` (`Person`, `User`). `EntityIdentityTests` im ersten Lab zeigt die drei Fehler oben.

## JSON

Entities direkt als JSON auszugeben ist in den Labs erlaubt. Dann gilt:

- Passwort-Hash mit `@JsonIgnore` ausblenden.
- Bidirektionale Beziehungen mit `@JsonManagedReference`/`@JsonBackReference` oder `@JsonIgnoreProperties` kappen.
- Spring Boot lässt Open Session in View an. Eine lazy Beziehung lädt deshalb noch beim Schreiben des JSON nach, auch ohne Transaktion im Controller.
