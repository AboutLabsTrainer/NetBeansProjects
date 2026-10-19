package tech.eta.dataloader_d.commandline;

import org.jspecify.annotations.NonNull;

import java.util.List;
import java.util.Optional;

// currently not in use... Should not be public beyond the commandline package. It would be used in the CLIRepository
record ParseResult(Optional<Command> command, List<String> arguments, @NonNull String help) {
    public boolean isHelp() {
        return !help.trim().isEmpty() || command.isEmpty();
    }
}
