package tech.eta.dataloader_d.logging;

import org.jspecify.annotations.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tech.eta.dataloader_d.logging.models.LogLevel;

import java.util.List;

@RestController
@RequestMapping("/api/logs")
public class LogController {

    private static final Logger logger = LoggerFactory.getLogger(LogController.class);

    private final LogService logService;

    public LogController(LogService logService) {
        this.logService = logService;
    }

    @PostMapping("/create") // => /api/logs/create
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("isAuthenticated()")
    public void create(@RequestBody CreateLogRequest request) {
        logService.create(request.level(), request.message());
    }

    //@GetMapping
    @GetMapping("/recent")
    @PreAuthorize("hasRole('ADMIN')")
    public @NonNull List<?> recent() {
        return logService.getRecent();
    } // List<AppLog> verwenden?
//    public @NonNull List<?> recent(@RequestParam(defaultValue = "0") int number) { return appender.getRecent(number); }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public @NonNull List<?> all() {
        return logService.findAll();
    }

    public record CreateLogRequest(LogLevel level, String message) {
    }
}
