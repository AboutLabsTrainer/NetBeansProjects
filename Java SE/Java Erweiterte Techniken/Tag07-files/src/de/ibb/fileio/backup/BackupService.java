package de.ibb.fileio.backup;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Kopiert eine Datei unter einem neuen Namen mit Zeitstempel, damit das Original erhalten bleibt.
 */
public final class BackupService {
    private static final DateTimeFormatter TS = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");

    private BackupService() {}

    public static Path backupWithTimestamp(Path sourceFile, Path backupDir) throws IOException {
        Files.createDirectories(backupDir);

        String fileName = sourceFile.getFileName().toString();
        String stamp = LocalDateTime.now().format(TS);

        String targetName;
        int dot = fileName.lastIndexOf('.');
        if (dot > 0 && dot < fileName.length() - 1) {
            String base = fileName.substring(0, dot);
            String ext = fileName.substring(dot); // includes dot
            targetName = base + "_" + stamp + ext;
        } else {
            targetName = fileName + "_" + stamp;
        }

        Path target = backupDir.resolve(targetName);
        Files.copy(sourceFile, target, StandardCopyOption.REPLACE_EXISTING);
        return target;
    }
}

