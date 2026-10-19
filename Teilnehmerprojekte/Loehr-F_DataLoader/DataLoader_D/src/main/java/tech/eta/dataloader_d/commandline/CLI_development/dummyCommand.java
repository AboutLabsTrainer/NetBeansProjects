package tech.eta.dataloader_d.commandline.CLI_development;

import lombok.Getter;
import org.jspecify.annotations.NonNull;

import java.util.*;
import java.util.stream.Stream;

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
public enum dummyCommand {
    A( "A") {
        @Override
        public List<dummyCommand> getCommands() {
            return List.of(A_a, A_b);
        }
    },
    A_a("a") {
        @Override
        public List<dummyCommand> getCommands() {return List.of();}
    },
    A_b( "b") {@Override public List<dummyCommand> getCommands() {return List.of();}},
    B( "B") {@Override public List<dummyCommand> getCommands() {return List.of(B_a, B_b);}},
    B_a( "a") {@Override public List<dummyCommand> getCommands() {return List.of();}},
    B_b( "b") {@Override public List<dummyCommand> getCommands() {return List.of();}};

    abstract public List<dummyCommand> getCommands();
    public List<dummyCommand> getCommandsB(){return List.of();};
    public List<dummyCommand> getCommandsByNamespace() {
        // this wont throw if a name was changed and no longer is in the pseudo-namespace. Also slow.
        return Stream.of(dummyCommand.values()).filter(c->c.name().startsWith(this.name()+"_")).toList();
    }

    @Getter
    private final String cmdText;

    dummyCommand(@NonNull String cmdText) {
        this.cmdText = cmdText.toUpperCase();
    }

}
