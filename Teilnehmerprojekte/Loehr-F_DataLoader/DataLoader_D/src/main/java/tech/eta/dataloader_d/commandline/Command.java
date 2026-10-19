package tech.eta.dataloader_d.commandline;

import lombok.Getter;
import org.jspecify.annotations.NonNull;

import java.util.*;

import java.util.function.Predicate;

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
public enum Command {
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

    // Where the subcommands are stored. Override for dynamic list. Be careful when overriding both getters at once.
    @Getter // Override for dynamic list.
    private final List<String> subCommandNames = new ArrayList<>();
    @Getter // Override for dynamic list. If overridden you must take care of your non-dynamic values yourself.
    private final List<Command> subCommands = new ArrayList<>();

    static {
        // Validation and static linking of command graph
        for (Command command : Command.values()) {
            // Mixing both definition styles is bad practice and prone to subtle bugs.
            // Restricting to one approach per parent command also keeps the implementation straightforward.
            if (!command.subCommands.isEmpty() && !command.subCommandNames.isEmpty()) throwAmbiguousRelation(command);
            // Todo: Should we use the getters here? If overridden they're not safe against loops...
            for (String name : command.subCommandNames) command.subCommands.add(Command.valueOf(name));
            for (Command child : command.subCommands) command.subCommandNames.add(child.name());
        }
    }


    private static void throwAmbiguousRelation(Command parent) {
        throwAmbiguousRelation(parent, parent.subCommands, "getSubCommands()");
    }
    private static void throwAmbiguousRelation(Object parent, List<?> children, String getterName) {
        throw new IllegalArgumentException("""
            Bidirectional definition of relationship: Declared by both parent and children.
            To avoid ambiguity, maintain a single definition pattern per parent:
              - Child-driven (recommended) via varargs: CHILD (..., PARENTs)
              - Parent-driven (if cyclic)  via strings: PARENT(..., childNames, ...)
            For dynamic/runtime lists, override '%s'.

            Context:
              Parent:   %s
              Children: %s
            """.formatted(getterName, parent, children));
    }


    //    abstract public List<Command> getCommands();
    public List<Command> getCommandsByNamespace() {
        // this won't throw if a name was changed and no longer is in the pseudo-namespace. Also slow.
        return Command.commandValues.stream().filter(c->c.name().startsWith(this.name()+"_")).toList();
    }

    private final boolean command;

    @Getter
    private final String cmdText;

//    // default implementation. feel free to override
//    public boolean matches(@NonNull String text, int offset, List<Command> trace) {
//    //    return text.split(" ", 1)[0].equalsIgnoreCase(getCmdText());
//        String cmd = getCmdText();
//        int cmdLength = offset + cmd.length();
//        int textLength = text.length();
//        if (cmdLength < textLength) return false;
//        if (cmdLength > textLength) return text.substring(offset, cmdLength+1).equalsIgnoreCase(cmd + " ");
//        return text.substring(offset, cmdLength).equalsIgnoreCase(cmd);
//    }
//
//    public Command parseNextSubcommand(String text, int offset, List<Command> trace) {
//        for (Command command : getSubCommands()) {
//            if (command.matches(text, offset, )) return command;
//        }
//    }

    private static @NonNull List<String> tokenize(@NonNull String command, boolean splitOnAnyWhitespace) {
        // TODO: refactor to use this...
        //      StringTokenizer tokenizer = new StringTokenizer(command);
        List<String> tokens = new ArrayList<>();
        StringBuilder token = new StringBuilder();

        char quoted = 0;  // something not a quote to compare against, dual used as isQuoted marker
        char escaped = 0; // something not an escape char to compare against, dual used as isEscaped marker
        Predicate<Character> splitHere = splitOnAnyWhitespace ? Character::isWhitespace : c->c==' ';

        for (char c : command.toCharArray()) {
            // catch escaped
            if (c == '\\') escaped = escaped==0 ? c : 0; // set escaped/unescaped
            // catch quotes
            if ((c == '"' || c == '\'') && escaped == 0) {
                if (quoted == 0) quoted=c;
                else if (quoted == c) quoted=0;
            }
            // split on unquoted, unescaped whitespaces/spaces
            if (splitHere.test(c) && quoted == 0 && escaped == 0) {
                tokens.add(token.toString());
                token.setLength(0);
            }
            // append the rest
            token.append(c);
        }
        if (!token.isEmpty()) {
            tokens.add(token.toString());
        }
        return tokens;
    }

    public static List<Command> parseFull(@NonNull String cmdLine, int offset, List<Command> trace) {
        int maxLength = cmdLine.length();
        int start = offset;
//        String[] split = cmdLine.split(" ");
        List<String> split = tokenize(cmdLine, true);
        List<Command> commands = new ArrayList<>(); // we should perhaps store the offsets too?
        List<Command> subCommands = (trace.isEmpty())?List.of(Command.values()):trace.getLast().getSubCommands();


        for (String s : split) {
//            if (start >= maxLength) {start=maxLength; break;}
            boolean found = false;
            for (Command command : subCommands) {
                if (command.getCmdText().equalsIgnoreCase(s)) {
                    commands.add(command);
                    subCommands = command.getSubCommands();
                    found = true;
                    break;
                }
            }
            if (!found) break;
        }
        return commands;
    }

    public record CommandOffset(Command command, int offset) { }

    public Optional<CommandOffset> parseStep(@NonNull String text, int start, @NonNull List<CommandOffset> trace) {
        List<Command> commands = (trace.isEmpty())?List.of(Command.values()):trace.getLast().command().getSubCommands();
        int textLength = text.length();
        for (Command command : commands) {
            final String cmdText = command.getCmdText();
            final int end = start + cmdText.length();
            if (end < textLength && text.substring(start, end + 1).equalsIgnoreCase(cmdText+" ")) {
                return Optional.of(new CommandOffset(command, end + 1));
            }
            if (end == textLength && text.substring(start, end).equalsIgnoreCase(cmdText)) {
                return Optional.of(new CommandOffset(command, end));
            }
        }
        return Optional.empty();
    }

    // over the list of tokens recurse and produce a chain of commands and subcommands
    public void parseStep(@NonNull List<String> tokens, int start, @NonNull List<CommandOffset> trace) {
        List<Command> commands = (trace.isEmpty()) ? List.of(Command.values()) : trace.getLast().command().getSubCommands();
        String token = tokens.get(start);
        for (Command command : commands) {
            if (command.matchesText(token)) {
                List<String> args = command.parseArgs(tokens, start+1);
                trace.add(new CommandOffset(command, start + 1 + args.size()));
                command.parseStep(tokens, start + 1 + args.size(), trace);
                break;
            }
        }
    }

    // override to change how arguments are read.
    public List<String> parseArgs(@NonNull List<String> tokens, int start) {return List.of();}

    public boolean matchesText(String cmdText) {
        if (caseSensitive) return getCmdText().equals(cmdText);
        return getCmdText().equalsIgnoreCase(cmdText);
    }

    public List<CommandOffset> iterParse(@NonNull String text, int start, @NonNull List<CommandOffset> trace) {
        // step through text, parse what you can, then return
        int textLength = text.length();
        List<CommandOffset> commands = new ArrayList<>();
        int offset = start;
        while (true) {
            final Optional<CommandOffset> step = parseStep(text, offset, trace);
            if (step.isEmpty()) break;
            else {
                commands.add(step.get());
                offset = step.get().offset();
            }
        }
        return commands;
    }

    // concept: two methods for parsing:
    //  1. a static method that grabs the first token, loops over all toplevel commands, calling matchesText on all.
    //  2. a private method that ...
    //  a step-tokenizer method...

    private static @NonNull List<String> stepTokenize(@NonNull String command, boolean splitOnAnyWhitespace) {
        // TODO: refactor to use this...
        //      StringTokenizer tokenizer = new StringTokenizer(command);
        List<String> tokens = new ArrayList<>();
        StringBuilder token = new StringBuilder();

//        token.substring(token.length()-delim.length()); // this allows me to ompare with the end of the builder => mutychar-delimiter
        // predicate on two input params? one is the string builder, one is the char?
        // return 3 ints: start, stop, stop + delim.width ...

        char quoted = 0;  // something not a quote to compare against, dual used as isQuoted marker
        char escaped = 0; // something not an escape char to compare against, dual used as isEscaped marker
        Predicate<Character> splitHere = splitOnAnyWhitespace ? Character::isWhitespace : c->c==' ';

        for (char c : command.toCharArray()) {
            // catch escaped
            if (c == '\\') escaped = escaped==0 ? c : 0; // set escaped/unescaped
            // catch quotes
            if ((c == '"' || c == '\'') && escaped == 0) {
                if (quoted == 0) quoted=c;
                else if (quoted == c) quoted=0;
            }
            // split on unquoted, unescaped whitespaces/spaces
            if (splitHere.test(c) && quoted == 0 && escaped == 0) {
                tokens.add(token.toString());
                token.setLength(0);
            }
            // append the rest
            token.append(c);
        }
        if (!token.isEmpty()) {
            tokens.add(token.toString());
        }
        return tokens;
    }







    // getCommand might be dangerous. Why? Because it may happen two (sub) commands have the same text.
    private static final Map<String, Command> commandMap = new HashMap<>();

    public Optional<Command> getCommand(@NonNull String name) {
        if (commandMap.isEmpty()) buildCommandMap();

        Command command = commandMap.get(name);
        if (command == null) return Optional.empty();
        return Optional.of(command);
    }

    private void buildCommandMap() {
        for (Command command : Command.commandValues) {
//            commandMap.put(command.getCmdText(), command); // what f I have two commands of the same text?
            commandMap.put(command.name(), command); // this works. the names are unique
        }
    }


    private boolean caseSensitive = false;

    // constructor may accept (multiple) parents to store subcommand with
    // TODO: multiple cmdTexts? Also blank cmdText for gobble-any and gobbleNone. What if not uppercased?
    Command(boolean command,
            @NonNull String cmdText,
            boolean isCaseSensitive,
            @NonNull List<String> childNames, // this allows static declared loops
            Command... parentCommands) {
        this(command, cmdText, childNames, parentCommands);
        this.caseSensitive = isCaseSensitive;
    }
    Command(boolean command,
            @NonNull String cmdText,
            boolean isCaseSensitive,
            Command... parentCommands) {
        this(command, cmdText, parentCommands);
        this.caseSensitive = isCaseSensitive;
    }
    Command(boolean command,
            @NonNull String cmdText,
            @NonNull List<String> childNames, // this allows static declared loops
            Command... parentCommands) {
        this(command, cmdText, parentCommands);
        this.subCommandNames.addAll(childNames);
    }
    Command(boolean command,
            @NonNull String cmdText,
            Command... parentCommands) {
        this.command = command;
        this.cmdText = cmdText.toUpperCase();
        for (Command parentCommand : parentCommands) parentCommand.subCommands.add(this);
    }



    // List constants, etc
    private boolean isCommand() {
        return this.command;
    }

    public static final List<String> allNames = Arrays.stream(values())
            .map(Enum::name)
            .toList();

    public static final List<Command> commandValues = Arrays.stream(values())
            .filter(Command::isCommand)
            .toList();

    public static final List<String> commandTexts = commandValues.stream()
            .map(Command::getCmdText)
            .toList();
}
