package tech.eta.dataloader_d.commandline.CLI_development;

import lombok.Getter;
import lombok.NonNull;
import java.util.ArrayList;
import java.util.List;

import java.util.concurrent.ConcurrentHashMap;

//public class UserCache {
//    // Die threadsichere Map im Hintergrund
//    private final ConcurrentHashMap<String, List<String>> cache = new ConcurrentHashMap<>();
//
//    public List<String> getValues(String userId) {
//        // CLEAN & SICHER:
//        // 1. Wenn userId existiert: Gibt den Wert sofort ohne Sperre zurück.
//        // 2. Wenn nicht: Sperrt NUR diesen Key, führt das Lambda EXAKT EINMAL aus,
//        //    speichert das Ergebnis und gibt es zurück. Alle anderen Threads für diesen Key warten.
//        return cache.computeIfAbsent(userId, id -> loadFromDatabase(id));
//    }
//
//    private List<String> loadFromDatabase(String id) {
//        return List.of("Daten für " + id); // Teure Operation
//    }
//}


// Hierarchy defined by supplying parent => strictly noncyclic DAGs/trees
public enum dummyCommand_F {
    // 1. Hierarchie rein über die Struktur definieren
    A("A"), // A besitzt die Sub-Kommandos "a" und "b"
    A_a("a", A) {
        protected List<dummyCommand_F> mySubs(){return List.of(A_b);}}, // this will add children (cached)
    A_a_a("no", A_a),
    A_b("b", A) {public List<dummyCommand_F> getSubCommands(){return List.of(A_a);}}, // this will replace the caching method

    B("B"),
    B_a("a", B, A),
    B_b("b", B);

    @Getter
    private final String cmdText;

    // here we keep the commands to lazy cache
    private static final ConcurrentHashMap<dummyCommand_F, List<dummyCommand_F>> subcommandsMap = new ConcurrentHashMap<>();
    public List<dummyCommand_F> getSubCommands() {
        // CLEAN & SICHER:
        // 1. Wenn userId existiert: Gibt den Wert sofort ohne Sperre zurück.
        // 2. Wenn nicht: Sperrt NUR diesen Key, führt das Lambda EXAKT EINMAL aus,
        //    speichert das Ergebnis und gibt es zurück. Alle anderen Threads für diesen Key warten.
        return subcommandsMap.computeIfAbsent(this, dummyCommand_F::mySubs);
    }

    protected List<dummyCommand_F> mySubs() {return subCommands;} //must not be private

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
//    private boolean cached=false;
    private final List<dummyCommand_F> subCommands = new ArrayList<>();
//    synchronized public List<dummyCommand_F> getSubCommands() {
//        if (!cached) {
//            subCommands.addAll(this.mySubs());
//            cached=true;
//        }
//        return subCommands;
//    }

    // Konstruktor für Kind-Befehle
    dummyCommand_F(@NonNull String cmdText, dummyCommand_F... parentCommands) {
        this.cmdText = cmdText.toUpperCase();
        for (dummyCommand_F parentCommand : parentCommands) {
            parentCommand.subCommands.add(this);
        }
    }

    // i could accept a Runnable or better yet a supplier (a function body or a lambda)
    // That can be replaced on the fly...

    public static void main(String[] args) {
        for (dummyCommand_F command : dummyCommand_F.values()) {
            System.out.println(command + " => " + command.getSubCommands());
        }
    }
}
