package tech.eta.dataloader_d.commandline.CLI_development;

import lombok.Getter;
import lombok.NonNull;
import java.util.ArrayList;
import java.util.List;

// Hierarchy defined by supplying parent => strictly noncyclic DAGs/trees
public enum dummyCommand_E {
    // 1. Hierarchie rein über die Struktur definieren
    A("A"), // A besitzt die Sub-Kommandos "a" und "b"
    A_a("a", A) {List<dummyCommand_E> mySubs(){return List.of(A_b);}}, // this will add children (cached)
    A_a_a("no", A_a),
    A_b("b", A) {public List<dummyCommand_E> getSubCommands(){return List.of(A_a);}}, // this will replace the caching method

    B("B"),
    B_a("a", B, A),
    B_b("b", B);

    @Getter
    private final String cmdText;

//    private final List<dummyCommand_E> subCommands = new ArrayList<>();

    // three states for the cache: empty, unset and full.
    // That won't work with a final optional, since optionals are immutable and wouldn't accept the list.
    // We could A) discard the final (bad) B) make an optional of a final list C) use a boolean and the final list
    // D) use a non-final list set to null and handle the exception explicitely (clean single-threaded standard)
    // E) use AtomicReference to make the field final (not the content though...)
//    private final AtomicReference<List<dummyCommand_E>> mySubCommandsCache = new AtomicReference<>(null);
//    public List<dummyCommand_E> getSubCommands() {
//        // Wenn null, wird die Liste atomar geladen und gesetzt
//        return mySubCommandsCache.updateAndGet(
//                existing -> existing != null ? existing : Collections.unmodifiableList(mySubs())
//        );
//    }
//    what if i use an atomic ref kept null and assign a unmodifyable and empty a modifyable temp list away?
//    subCommands_temp accepts the children and

    // the best choice might stll be the one using the boolean. it allows children to add without preventing the parnts access via method
    List<dummyCommand_E> mySubs() {return List.of();} //must not be private
    private boolean cached=false;
    private final List<dummyCommand_E> subCommands = new ArrayList<>();
    synchronized public List<dummyCommand_E> getSubCommands() {
        if (!cached) {
            subCommands.addAll(this.mySubs());
            cached=true;
        }
        return subCommands;
    }

    // Konstruktor für Kind-Befehle
    dummyCommand_E(@NonNull String cmdText, dummyCommand_E... parentCommands) {
        this.cmdText = cmdText.toUpperCase();
        for (dummyCommand_E parentCommand : parentCommands) {
            parentCommand.subCommands.add(this);
        }
    }

    // i could accept a Runnable or better yet a supplier (a function body or a lambda)
    // That can be replaced on the fly...

    public static void main(String[] args) {
        for (dummyCommand_E command : dummyCommand_E.values()) {
            System.out.println(command + " => " + command.getSubCommands());
        }
    }
}
