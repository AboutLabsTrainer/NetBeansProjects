package tech.eta.dataloader_d.commandline.CLI_development;

import lombok.Getter;
import lombok.NonNull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum DummyCommand_B {
    // 1. Hierarchie rein über die Struktur definieren
    A("A", "a", "b"), // A besitzt die Sub-Kommandos "a" und "b"
    A_a("a"),
    A_b("b"),

    B("B", "a", "b"),
    B_a("a"),
    B_b("b");

    @Getter
    private final String cmdText;

    // Temporäres Array für die Zuordnung während der Initialisierung
    private final String[] subCommandTexts;

    // Der Cache für die fertigen Listen (Zugeordnet per Enum-Instanz)
    private static final Map<DummyCommand_B, List<DummyCommand_B>> CACHE;

    // Konstruktor für Hauptbefehle mit Kindern
    DummyCommand_B(@NonNull String cmdText, String... subCommandTexts) {
        this.cmdText = cmdText.toUpperCase();
        this.subCommandTexts = subCommandTexts;
    }

    // Konstruktor für Endpunkte (Blätter)
    DummyCommand_B(@NonNull String cmdText) {
        this.cmdText = cmdText.toUpperCase();
        this.subCommandTexts = new String[0];
    }

    // Statischer Block: Wird EINMALIG ausgeführt, wenn das Enum geladen wird
    static {
        CACHE = Stream.of(values()).collect(Collectors.toMap(
                command -> command,
                command -> {
                    if (command.subCommandTexts.length == 0) {
                        return List.of(); // Keine Kinder
                    }

                    List<DummyCommand_B> children = new ArrayList<>();
                    String prefix = command.name() + "_"; // Z.B. "A_"

                    for (String subText : command.subCommandTexts) {
                        // Wir suchen die exakte Enum-Instanz anhand des erwarteten Namens
                        // Ändert sich ein Name, fliegt hier SOFORT beim Anwendungsstart eine Exception!
                        String expectedEnumName = prefix + subText;
                        try {
                            children.add(DummyCommand_B.valueOf(expectedEnumName));
                        } catch (IllegalArgumentException e) {
                            throw new IllegalStateException(
                                    "Fehler im Namensraum: " + command.name() +
                                            " erwartet ein Sub-Kommando namens " + expectedEnumName + ", das nicht existiert!"
                            );
                        }
                    }
                    return Collections.unmodifiableList(children);
                }
        ));
    }

    // O(1) Performance - Holt die Liste direkt aus der Map
    public List<DummyCommand_B> getCommands() {
        return CACHE.get(this);
    }
}
