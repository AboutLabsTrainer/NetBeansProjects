package tech.eta.dataloader_d.commandline.CLI_development;

import lombok.Getter;
import org.jspecify.annotations.NonNull;

import java.util.*;

/* We want a tree structure for our commands, without defining vast numbers of classes.
 * (Yes we could subclass for subtrees, but that would be ... cumbersome and produce many classes)
 * Thus there are a few ways to make a pseudo-tree thing using a method to get child elements.
 *  - we could push a lambda via the constructor, replacing a dummy method with it.
 *  - we could create anonymous subclasses (using {} behind the instance) to override a method (abstract or not).
 *  - we could probably replace a dummy method via assignment in anonymous subclasses.
 *  - we might be able to override fixed lists for anonymous subclasses or via the constructor,
 *      but that may fail as we havent constructed the other classes yet.
 * => for now, we shall go with overriding a default method, which tries to get subcommands by pseudo-namespace
 */
public enum Command__old {
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
    ECHO(true, "ECHO");

//    abstract public List<Command> getCommands();
    public List<Command__old> getCommandsB(){return List.of();};
    public List<Command__old> getCommandsByNamespace() {
        // this wont throw if a name was changed and no longer is in the pseudo-namespace. Also slow.
        return Command__old.commandValues.stream().filter(c->c.name().startsWith(this.name()+"_")).toList();
    }

    private final boolean command;

    @Getter
    private final String cmdText;

    private static final Map<String, Command__old> commandMap = new HashMap<>();

    public Optional<Command__old> getCommand(@NonNull String name) {
        if (commandMap.isEmpty()) buildCommandMap();

        Command__old command = commandMap.get(name);
        if (command == null) return Optional.empty();
        return Optional.of(command);
    }

    private void buildCommandMap() {
        for (Command__old command : Command__old.commandValues) {
            commandMap.put(command.getCmdText(), command);
        }
    }

    Command__old(boolean command,
                 @NonNull String cmdText) {
        this.command = command;
        this.cmdText = cmdText.toUpperCase();
    }

    private boolean isCommand() {
        return this.command;
    }

    public static final List<String> allNames = Arrays.stream(values())
            .map(Enum::name)
            .toList();

    public static final List<Command__old> commandValues = Arrays.stream(values())
            .filter(Command__old::isCommand)
            .toList();

    public static final List<String> commandTexts = commandValues.stream()
            .map(Command__old::getCmdText)
            .toList();
}
