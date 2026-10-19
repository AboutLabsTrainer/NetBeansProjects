package tech.eta.dataloader_d.commandline;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class CLIController {
    private final int MAXLENGTH = 20;
    private final List<CommandlineHistory> history = new ArrayList<>(MAXLENGTH);
    private final CommandlineRepository commandlineRepository;

    private final SimpMessagingTemplate messagingTemplate; // for sending to the websocket...

    public CLIController(CommandlineRepository commandlineRepository, SimpMessagingTemplate messagingTemplate) {
        this.commandlineRepository = commandlineRepository;
        this.messagingTemplate = messagingTemplate;
        testrun_on_startup();
    }

    private void testrun_on_startup() { // this is just to test the entire CLI and to add *something* to the history...
        call("echo Startup completed. Command line is available now.");
    }

    private Optional<String> normalizeAndValidate(@Nullable String command) {
        if (command == null) return Optional.empty();
        String trimmedCommand = command.trim();
        if (trimmedCommand.isEmpty()) return Optional.empty();
        return Optional.of(trimmedCommand);
    }

    private void append(@NonNull CommandlineHistory cliHistory) {
        history.add(cliHistory);
        synchronized (this) {
            messagingTemplate.convertAndSend("/topic/cli", cliHistory);
        }
    }

    // 1. Return the history (to display the calls)
    @GetMapping("/api/cli/list")
    public List<CommandlineHistory> list(@NonNull Model model) {
        model.addAttribute("history", history); // this *should* be accessible via th:each
        return history;
    }

    // 2. Call something    NOTE: we need to use JavaScript, else the action would force a redirect...
    // Todo: the same way we have the messaging template in the realAppender, we shall do here to supports pushing our commandline results
    @PostMapping("/api/cli/call")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public void call(@RequestBody @NonNull String command) { // should I do a model here or go requestparam?
        // Note: For nullables I would have to *explicitly* use: @RequestBody(required=false) @Nullable String command
        normalizeAndValidate(command).ifPresent(
                commandline -> append(commandlineRepository.parseAndRunCommand(command))
        );
    }
}
