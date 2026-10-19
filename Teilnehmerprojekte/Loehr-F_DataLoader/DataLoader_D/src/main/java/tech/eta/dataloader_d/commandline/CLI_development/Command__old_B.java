package tech.eta.dataloader_d.commandline.CLI_development;

import lombok.Getter;
import org.jspecify.annotations.NonNull;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/* We want a tree structure for our commands, without defining vast numbers of classes. There are far too many options.
 * (Yes we could subclass for subtrees, but that would be ... cumbersome and produce many classes)
 * Thus there are a few ways to make a pseudo-tree thing using a method to get child elements.
 *  A) invert order (leafs first definition)
 *      => simply assign the children. easy to use/implement, but feels weird. Tree or DAG, strictly non-cyclic
 *  B) constructor takes parent (or multipe parents), root first defnition
 *      => feels natural. easy imlementation. fast. Tree or DAG, strictly non-cyclic
 *  C) lambda fed constructor: replace a dummy method with the lambda, postponing usage (overhead for list creation)
 *  D) anonymous subclass (using {} behind the instance) to override a method (abstract or not) returning the items
 *  E) C or D used to override a private method returning the items. The public method is cached (unmodifiable list).
 *      (C, D and E can go cyclic)
 *  F) we might be able to override fixed lists for anonymous subclasses or via the constructor,
 *      but that may fail if we haven't constructed the other classes yet. While the compiler might not throw an error
 *      the runtime certanly will
 *  G) a default method may try fetching parents or children using the name spacing rules (tree only),
 *      but that wouldn't catch renames and could produce hard to track bugs
 *  H) feeding parents via lambda wouldn't work, as it would make passing along the children uncertain.
 *      A solution might be to cache a creation step at which we store them, but that's ... overcomplicated
 *  I) Why not combine B and DE? Easy to use and no boilerplate normally, explicite override for cyclic/special cases.
 * => Note that A and B produce simple code and stable, fast lists, but are unable to produce circular graphs
 *      C, D and E can go cyclic, but may be slower and more boilerplate-heavy. further there may be overhead for list creation
 * => for now, we shall go with overriding a default method, which tries to get subcommands by pseudo-namespace
 */
public enum Command__old_B {
    __UNKNOWN__(false, "") {
//        @Override
//        public List<Command> getCommands() {
//            return List.of(LOG, SQL);
//        }
    },
    __HELP__(false, "HELP") {

//        @Override
//        public List<Command> getCommands() {return commandValues;}
    },

    LOG(true, "LOG"),
    SQL(true, "SQL"),
    USER(true, "USER"),
    ECHO(true, "ECHO"),
    _help(false, "--help", LOG, SQL, USER, ECHO) {};

    // Where the subcommands are stored. overriding mySubs allows for cyclic graphs.
    protected List<Command__old_B> mySubs() {return List.of();}
    private final List<Command__old_B> subCommands = new ArrayList<>();
    // the actual cache.
    private static final Map<Command__old_B, List<Command__old_B>> subCommandsMap = new ConcurrentHashMap<>();
    // default getter. Override for dynamic lists. If overridden you must take care of your non-dynamic values yourself.
    public List<Command__old_B> getSubCommands(){
        return subCommandsMap.computeIfAbsent(
                this,
                k -> {
                    subCommands.addAll(mySubs());
                    return Collections.unmodifiableList(subCommands);
                }
        );
    };


    // where to store (cached) subcommands. If given parent-commands, this is where subcommands will register themselves
//    private final List<Command> subCommands = new ArrayList<>();

    // override this to make subcommands-list cached
//    protected List<Command> mySubCommands(){return List.of();}

    // There's two approaches: using double-checked locking is slightly slower if syncronizing, as threads need to sleep
    // but it prevents costly recalculation, so use it if calculation is high in cost.
    // AtomicReferrence is cheap as no threads sleep, but it can't prevent parallel calculation, only parallel assignment

    // approach using AtomicReference: Sleek and generally faster if we expect cheap calculation as it skips syncronizing threads
//    private final AtomicReference<List<Command>> subCommandsCached = new AtomicReference<>(null);
//    public List<Command> getSubCommands(){
////        if (subCommandsCached.get() == null) subCommandsCached.set(mySubCommands()); // if we prevent using parent AND child-override at the same time, we could pass through subCommands
//        if (subCommandsCached.get() == null) {
//            subCommands.addAll(mySubCommands());
//            subCommandsCached.set(subCommands);
//        }
//        return subCommandsCached.get();
//    }

//    // approach using double-checked locking: Good if generation is costly, but slightly slower if generation is cheap.
//    private volatile boolean subCommandsCached = false;
//    public List<Command> getSubCommands(){
//        if (!subCommandsCached){ // check fast tu prevent unnecessary locking
//            synchronized (this) {if (!subCommandsCached){ // second check in syncronized to prevent costly stuff
//                subCommands.addAll(mySubCommands());
//                subCommandsCached = true;
//            }}
//        }
//        return subCommands;
//    }


//    List<dummyCommand_E> mySubs() {return List.of();} //must not be private
//    private boolean cached=false;
//    private final List<dummyCommand_E> subCommands = new ArrayList<>();
//    synchronized public List<dummyCommand_E> getSubCommands() {
//        if (!cached) {
//            subCommands.addAll(this.mySubs());
//            cached=true;
//        }
//        return subCommands;
//    }

    //    abstract public List<Command> getCommands();
    public List<Command__old_B> getCommandsByNamespace() {
        // this wont throw if a name was changed and no longer is in the pseudo-namespace. Also slow.
        return Command__old_B.commandValues.stream().filter(c->c.name().startsWith(this.name()+"_")).toList();
    }

    private final boolean command;

    @Getter
    private final String cmdText;

    private static final Map<String, Command__old_B> commandMap = new HashMap<>();

    public Optional<Command__old_B> getCommand(@NonNull String name) {
        if (commandMap.isEmpty()) buildCommandMap();

        Command__old_B command = commandMap.get(name);
        if (command == null) return Optional.empty();
        return Optional.of(command);
    }

    private void buildCommandMap() {
        for (Command__old_B command : Command__old_B.commandValues) {
            commandMap.put(command.getCmdText(), command);
        }
    }


    // constructor may accept (multiple) parents to store subcommand with
    // TODO: multiple cmdTexts?
    Command__old_B(boolean command,
                   @NonNull String cmdText,
                   Command__old_B... parentCommands) {
        this.command = command;
        this.cmdText = cmdText.toUpperCase();
        for (Command__old_B parentCommand : parentCommands) parentCommand.subCommands.add(this);
    }



    // List constants, etc
    private boolean isCommand() {
        return this.command;
    }

    public static final List<String> allNames = Arrays.stream(values())
            .map(Enum::name)
            .toList();

    public static final List<Command__old_B> commandValues = Arrays.stream(values())
            .filter(Command__old_B::isCommand)
            .toList();

    public static final List<String> commandTexts = commandValues.stream()
            .map(Command__old_B::getCmdText)
            .toList();
}
