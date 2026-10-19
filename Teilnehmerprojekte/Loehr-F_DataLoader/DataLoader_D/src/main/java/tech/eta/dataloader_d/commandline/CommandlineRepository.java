package tech.eta.dataloader_d.commandline;

import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Repository;
import tech.eta.dataloader_d.logging.LogService;
import tech.eta.dataloader_d.logging.models.AppLog;
import tech.eta.dataloader_d.logging.models.LogLevel;

import java.util.*;

@Repository
public class CommandlineRepository {
    private final LogService logService;

    public CommandlineRepository(LogService logService) {
        this.logService = logService;
    }


    private static @NonNull String normalize(@NonNull String command) {
        return command.toUpperCase().strip();
    }


    // This is a prototype segment. The help for future commands
    private static @NonNull String helpForCommand(String command) {
        return switch (command) {
            case "USER" -> """
                USER
                
                Manage application users. Requires administrator privileges for create, update and delete.
                
                Usage:
                  USER create <name> --password=<password> [--roles=<roles>]
                  USER update <name> [--name=<new_name>] [--password=<password>] [--roles=<roles>]
                  USER delete <name>
                  USER <name>
                  USER --help
                
                Commands:
                  create                Create a new user with a mandatory password.
                  update                Update an existing user's name, password or roles.
                  delete                Permanently delete a user.
                  <name>                Without subcommand, list the user roles.
                
                Arguments:
                  <name>                The current or target username.
                
                Options:
                  --name=<new_name>     The new username when performing a rename.
                  --password=<password> The new password for the user.
                  --roles=<roles>       Comma-separated list of assigned roles.
                                        Must be the last argument! Defaults to --roles=USER
                  --help                Show this help screen.
                """;

            case "SQL" -> """
                SQL
                
                Execute a SQL statement. Requires administrator privileges.
                
                Usage:
                  SQL <statement>
                  SQL --help
                
                Arguments:
                  <statement>           SQL statement to execute.
                
                Examples:
                  SQL SELECT * FROM users
                  SQL "SELECT * FROM users WHERE id = 1"
                """;
            default -> "Unknown command. Usage: <" + String.join("|", Command.commandTexts) + "> [--help] [args]";
        };
    }


    private static int startsWithToken(String text, String token, int offset) {
        return startsWithToken(text, token, " ", offset);
    }

    private static int startsWithToken(String text, String token, String separator, int offset) {
        if (text.startsWith(token, offset)) {
            final int offsetToken = token.length();
            if (offset + offsetToken == text.length()) return offsetToken;
            if (text.startsWith(separator, offset + offsetToken)) return offsetToken + separator.length();
        }
        return 0;
    }

    @NonNull
    public CommandlineHistory parseAndRunCommand(@NonNull String commandline) {
        String cLine = commandline.strip(); // remove leading/trailing whitespaces
        String cmd = cLine.toUpperCase();

        // skip empty commands (blank input, blank output
        if (cmd.isEmpty()) return new CommandlineHistory(cLine, CommandState.SUCCESS, "");

        // check if we start with the help command. if so offset accordingly. Should we recognize help as a command? I think so.
        int offset = startsWithToken(cmd, "HELP", 0); // 4 if "help", 5 if "help <command>"
        if (offset == cmd.length()) {
            return runCommand(Command.__HELP__, cLine, offset, true);
        }

        boolean isHelp = offset > 0;
        Command command = isHelp ? Command.__HELP__ : Command.__UNKNOWN__;

        // fetch command  // todo: check if we can transform into split-command and get-by-name
        for (Command c : Command.commandValues) {
            final String name = c.getCmdText();
            final int offsetToken = startsWithToken(cmd, name, offset);
            if (offsetToken > 0) {
                offset += offsetToken;
                command = c;
                break;
            }
        }

        // fetch help request
        final int offsetToken = startsWithToken(cmd, "--HELP", offset);
        if (offsetToken > 0) {
            isHelp = true;
            offset += offsetToken;
        }

        // call the actual function dispatcher
        return runCommand(command, cLine, offset, isHelp);
    }

    private @NonNull CommandlineHistory runCommand(Command command,
                                                   @NonNull String commandline,
                                                   int offset,
                                                   boolean isHelp) {
        return switch (command) {
            case ECHO -> runEchoCommand(commandline, offset, isHelp);
            case LOG -> runLogCommand(commandline, offset, isHelp);
            // todo: implement those
            case SQL -> runNotImplemented(commandline, offset, isHelp);
            case USER -> runNotImplemented(commandline, offset, isHelp);
            // this is the default segment
            case __HELP__ -> new CommandlineHistory(commandline, CommandState.SUCCESS, helpGeneric());
            default -> {
                if (isHelp) yield new CommandlineHistory(commandline, CommandState.SUCCESS, helpGeneric());
                yield new CommandlineHistory(
                        commandline,
                        CommandState.ERROR,
                        "ERR: Unrecognized command. Try \"help\" for details."
                );
            }
        };
    }

    private static @NonNull String helpGeneric() {
        return """
                HELP [command]      -> Display usage and this help text.
                
                GENERAL USAGE: <command> [--help] [args]
                
                There are multiple commands.
                You may use "help <command>" or "<command> --help" to get detailed help.
                The currently supported commands are as follows:
                """ + String.join(", ", Command.commandTexts);
    }

    private static @NonNull CommandlineHistory helpNotImplemented(@NonNull String commandline, int offset) {
        return new CommandlineHistory(commandline, CommandState.FAILURE, "ERR: not yet implemented.");
    }

    private static @NonNull CommandlineHistory runNotImplemented(@NonNull String commandline, int offset, boolean isHelp) {
        if (isHelp) return helpNotImplemented(commandline, offset);
        return new CommandlineHistory(commandline, CommandState.FAILURE, "ERR: not yet implemented.");
    }

    private static @NonNull CommandlineHistory helpEchoCommand(@NonNull String commandline, int offset) {
        return new CommandlineHistory(commandline, CommandState.SUCCESS, "ECHO  echos the incoming text...");
    }

    private static @NonNull CommandlineHistory runEchoCommand(@NonNull String commandline, int offset, boolean isHelp) {
        if (isHelp) return helpEchoCommand(commandline, offset);
        return new CommandlineHistory(commandline, CommandState.SUCCESS, commandline.substring(offset));
    }

    private static @NonNull CommandlineHistory helpLogCommand(@NonNull String commandline, int offset) {
        // static. needs no further info
        return new CommandlineHistory(
                commandline,
                CommandState.SUCCESS,
                """
                        LOG
                        
                        Write a message to the application log.
                        
                        Usage:
                          LOG [--create] [level] <message>
                          LOG --list [--all] [--level=<level>] <message>
                          LOG --help
                        
                        Arguments:
                          [--create]    Create a log entry (default option)
                          [level]       Log-Level (INFO, NOTE, WARNING, ERROR), not case-sensitive, defaults to INFO
                          <message>     Message to write to the log, if creating.
                        
                          --list        List logs. Filter for level, if given.
                                        Filter for message string, if given (ignores case).
                          --all         Fetch all from database.
                        """);
    }

    private @NonNull CommandlineHistory runLogCommand(String commandline, int offset, boolean isHelp) {
        if (isHelp) return helpLogCommand(commandline, offset);

        String cmd = commandline.toUpperCase();
        String action = "create";
        boolean all = false;
        List<LogLevel> levels = new ArrayList<>(3);
        String message;

        int offsetToken = startsWithToken(cmd, "--CREATE", offset);
        if (offsetToken > 0) {
            offset += offsetToken;
            action = "create";
        }

        offsetToken = startsWithToken(cmd, "--LIST", offset);
        if (offsetToken > 0) {
            offset += offsetToken;
            action = "list";

            offsetToken = startsWithToken(cmd, "--ALL", offset);
            if (offsetToken > 0) {
                offset += offsetToken;
                all = true;
            }
        }

        for (LogLevel logLevel : LogLevel.values()) {
            offsetToken = startsWithToken(cmd, logLevel.name(), offset);
            if (offsetToken > 0) {
                offset += offsetToken;
                levels.add(logLevel);
            }
        }

        message = commandline.substring(offset);

        // todo: enum-tree for deep-parsing?

        if (action.equals("list")) {
            List<AppLog> result = logService.list(all, levels, message, true);
            System.out.println("RESULT:" + result);
            return new CommandlineHistory(commandline, CommandState.SUCCESS, result.toString());
        } else {
            if (levels.isEmpty()) {
                levels.add(LogLevel.INFO);
            }
            logService.create(levels.getFirst(), message);
            return new CommandlineHistory(commandline, CommandState.SUCCESS, "<log created>");
        }
    }

}

// TODO: clean up the types. put __help__ and __unknown__ in the commands where necessary,
