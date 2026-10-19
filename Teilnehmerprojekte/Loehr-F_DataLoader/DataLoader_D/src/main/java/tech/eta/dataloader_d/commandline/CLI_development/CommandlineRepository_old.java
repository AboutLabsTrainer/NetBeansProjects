package tech.eta.dataloader_d.commandline.CLI_development;


public class CommandlineRepository_old {}
/*

import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Repository;
import tech.eta.dataloader_d.logging.LogController;
import tech.eta.dataloader_d.commandline.CommandState;
import tech.eta.dataloader_d.commandline.CommandlineHistory;
import tech.eta.dataloader_d.repositories.CLI_development.CommandlineRepository_old.ParseResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CommandlineRepository_old {
    private enum Command {USER, LOG, SQL;

        public static List<String> names() {
            Command[] values = values();
            List<String> names = new ArrayList<>();
            for (Command value : values) {names.add(value.name());}
            return names;
        }
    }
    private record ParseResult(Optional<Command> command, List<String> arguments, @NonNull String help) {
        public boolean isHelp() {
            return !help.trim().isEmpty() || command.isEmpty();
        }
    }

    public static class ParseException extends RuntimeException {
        public ParseException(String message) {
            super(message);
        }
    }
    private static final String[] COMMANDS = {"USER", "LOG", "SQL"};
    private static final String[] HELPS = {"USER --help:" +
            "", "LOG", "SQL"};

    private static @NonNull String normalize(@NonNull String command) {
        return command.toUpperCase().strip();
    }

    private static @NonNull List<String> tokenize(@NonNull String command) {
        // TODO: refactor to use this...
        //      StringTokenizer tokenizer = new StringTokenizer(command);
        List<String> tokens = new ArrayList<>();
        StringBuilder token = new StringBuilder();

        char quote = 0; // something not a quote to compare against, dual used as isQuoted marker

        for (char c : command.toCharArray()) {
            // catch quotes
            if (c == '"' || c == '\'' ) {
                if (quote == 0) quote=c;
                else if (quote == c) quote=0;
            }
            // split on unquoted whitespaces
            if (Character.isWhitespace(c) && quote == 0) {
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

    private static Optional<Command> parseCommand(@NonNull String command) {
        try {return Optional.of(Command.valueOf(normalize(command).toUpperCase()));}
        catch (IllegalArgumentException e) {return Optional.empty();}
    }

    private static @NonNull ParseResult parseCommandLine(@NonNull String commandline) {
        final List<String> tokens = tokenize(commandline);
        Optional<Command> command = Optional.empty();
        boolean isHelp = false;
        int tokenSize = tokens.size();

        // check if we want help and if so strip the token that requests it
        if (tokens.size() >= 2) {
            if (tokens.get(1).equalsIgnoreCase("--help")) {
                tokens.remove(1);
                isHelp = true;
            }
            if (tokens.getFirst().equalsIgnoreCase("help")) {
                tokens.removeFirst();
                isHelp = true;
            }
        }

        // now we gather the command (Note that "help --help user" will result in calling "user --help")
        if (!tokens.isEmpty()) {
            String first = tokens.getFirst();
            tokens.remove(first);
            command = parseCommand(first);
        }
        String help = isHelp ? helpForCommand(command.map(Enum::name).orElse("-default-")) : "";
        return new ParseResult(command, tokens, help);
    }

    private static @NonNull String parse_help(@NonNull String command) {
        final String CMD = command.strip().toUpperCase();
        final List<String> tokens = tokenize(CMD);
        if (CMD.equals("HELP")) return helpForCommand("HELP");

        if (tokens.isEmpty()) return "ERR: no command";
        if (tokens.size() == 1) return "ERR: singular command";

        if (tokens.get(1).equals("--HELP")) return helpForCommand(tokens.getFirst()); // todo: add support for
        if (tokens.getFirst().equals("HELP")) return helpForCommand(tokens.get(1));   //    adding subcommand details
        return "";
    }


    private static @NonNull String helpForCommand(String command) {
        return switch (command) {
            case "USER" -> """
                    USER

                    Manage the current user.

                    Usage:
                      USER <name>
                      USER --help

                    Arguments:
                      <name>    User name to select or create.
                    """;

            case "SQL" -> """
                    SQL

                    Execute a SQL statement.

                    Usage:
                      SQL <statement>
                      SQL --help

                    Arguments:
                      <statement>    SQL statement to execute.

                    Examples:
                      SQL SELECT * FROM users
                      SQL "SELECT * FROM users WHERE id = 1"
                    """;

            case "LOG" -> """
                    LOG

                    Write a message to the application log.

                    Usage:
                      LOG <message>
                      LOG --help

                    Arguments:
                      <message>    Message to write to the log.
                    """;
            default -> "Unknown command. Usage: <"+String.join("|", Command.names())+"> [--help] [args]";
        };
//        return "There are multiple base commands. For details enter: help <command> \n available commands are ...";

    }

    private static @NonNull CommandlineHistory runLogCommand(String commandline) {
        String cmd = commandline.strip().toUpperCase();
        boolean isHelp = cmd.startsWith("help ");

        for (Command c : Command.values()) {
            final String name = c.name();

            if (cmd.startsWith(name + " --HELP")) { offset=(name + " --HELP").length(); isHelp=true; }
            if (cmd.startsWith("HELP " + name + " ")) { offset=(name + " HELP ").length(); isHelp=true; }
            if (cmd.equals("HELP " + name)) { offset=(name + " HELP").length(); isHelp=true; }
            if (cmd.startsWith(name)) { offset=name.length(); }

            if (offset>0) return runCommand(c, commandline, offset, isHelp);

        }

        if (cmd.startsWith("HELP")) {isHelp}
        if (commandline.startsWith("help") || commandline.startsWith("--help")) {}
        if (commandline.startsWith("--help", -5)) {}

        if (parsed.isHelp()) {};
        List<String> args = parsed.arguments();
        switch (args.getFirst()) {
            case "CREATE" -> logController.create(LogController.CreateLogRequest(args.get(1), args.))
        };
    }
    private static @NonNull CommandlineHistory runCommand(ParseResult commandline) {
        if (commandline.isHelp()) {};
        if (commandline.isHelp()) {};
    }
    private static @NonNull CommandlineHistory runCommand(ParseResult commandline) {
        if (commandline.isHelp()) {};
        if (commandline.isHelp()) {};
    }

    public static @NonNull CommandlineHistory call(@NonNull String command) {
        ParseResult parseResult = parseCommandLine(command);

        // if it was just for help ...
        if (parseResult.isHelp()) return new CommandlineHistory(command, CommandState.SUCCESS, parseResult.help());

        // else do what is requested
        if (parseResult.command().isPresent()) {
            CommandlineHistory result = switch (parseResult.command().orElseThrow()) {
                case Command.LOG -> runLogCommand(command);
                case Command.SQL -> runSqlCommand(parseResult);
                case Command.USER -> runUserCommand(parseResult);
            };
            return new CommandlineHistory(command, result.commandState(), result.result());
        }



        return runCommand(ParseResult);
//        state = parse_help(command);
//        return new CommandlineHistory(command, CommandState.FAILURE, "");
    }
}


// */
