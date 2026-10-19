package tech.eta.dataloader_d.commandline.CLI_development;

import lombok.Getter;
import lombok.NonNull;
import java.util.ArrayList;
import java.util.List;

// Hierarchy defined by supplying parent => strictly noncyclic DAGs/trees
public enum dummyCommand_C {
    // 1. Hierarchie rein über die Struktur definieren
    A("A"), // A besitzt die Sub-Kommandos "a" und "b"
    A_a("a", A),
    A_b("b", A),

    B("B"),
    B_a("a", B),
    B_b("b", B);

    @Getter
    private final String cmdText;

    @Getter
    private final List<dummyCommand_C> subCommands = new ArrayList<>();

    // Konstruktor für Kind-Befehle
    dummyCommand_C(@NonNull String cmdText, dummyCommand_C parentCommand) {
        this.cmdText = cmdText.toUpperCase();
        parentCommand.subCommands.add(this);
    }

    // Konstruktor für Wurzelbefehle
    dummyCommand_C(@NonNull String cmdText) {
        this.cmdText = cmdText.toUpperCase();
    }


    public static void main(String[] args) {
        System.out.println(dummyCommand_C.A.subCommands);
    }
}
