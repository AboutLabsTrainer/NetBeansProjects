package tech.eta.dataloader_d.commandline.CLI_development;

import lombok.Getter;
import lombok.NonNull;
import java.util.ArrayList;
import java.util.List;

// Hierarchy defined by supplying parent => strictly noncyclic DAGs/trees
public enum dummyCommand_D {
    // 1. Hierarchie rein über die Struktur definieren
    A("A"){
        @Override
        public List<dummyCommand_D> getSubCommands() {
            return List.of(B, B_a);
        }
    }, // A besitzt die Sub-Kommandos "a" und "b"
    A_a("a", A),
    A_b("b", A),

    B("B"),
    B_a("a", B, A),
    B_b("b", B);

    @Getter
    private final String cmdText;

    @Getter
    private final List<dummyCommand_D> subCommands = new ArrayList<>();

    // Konstruktor für Kind-Befehle
    dummyCommand_D(@NonNull String cmdText, dummyCommand_D... parentCommands) {
        this.cmdText = cmdText.toUpperCase();
        for (dummyCommand_D parentCommand : parentCommands) {
            parentCommand.subCommands.add(this);
        }
    }

    public static void main(String[] args) {
        System.out.println(dummyCommand_E.A_a.mySubs());
    }
}
