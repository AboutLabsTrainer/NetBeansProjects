package de.ibb.fileio.tasks;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import de.ibb.fileio.backup.BackupService;
import de.ibb.fileio.config.ConfigManager;
import de.ibb.fileio.csv.CsvParser;

/**
 * Profi-Aufgaben: Konfiguration mit Defaults, CSV als Objekte und eine Sicherungskopie mit Zeitstempel.
 */
public final class Level3Tasks {
    private Level3Tasks() {}

    public static void runAll(Path dataDir) throws IOException {
        System.out.println();
        System.out.println("---- Level 3 – Profi ----");

        Path configFile = dataDir.resolve("config").resolve("app.properties");
        Path csvFile = dataDir.resolve("csv").resolve("daten.csv");
        Path backupDir = dataDir.resolve("backup");
        Path backupSource = dataDir.resolve("text").resolve("input.txt");

        ensureSampleCsv(csvFile);
        ensureBackupSource(backupSource);

        configManagerWithDefaults(configFile);
        parseCsvToObjects(csvFile);
        backupWithTimestamp(backupSource, backupDir);
    }

    // [ ] Implementiere einen Config-Manager mit Default-Werten
    private static void configManagerWithDefaults(Path configFile) throws IOException {
        ConfigManager cfg = ConfigManager.loadOrCreate(configFile);
        cfg.set("last.run", java.time.LocalDateTime.now().toString());
        cfg.save("Anwendungskonfiguration - Auto-Update");

        System.out.println("[L3] Config geladen: " + cfg.getFile());
        System.out.println("[L3] app.name=" + cfg.getString("app.name", "n/a"));
        System.out.println("[L3] db.*:");
        cfg.getDbEntries().forEach((k, v) -> System.out.println("  " + k + "=" + v));
    }

    // [ ] Parse eine CSV-Datei in eine Liste von Objekten
    private static void parseCsvToObjects(Path csvFile) throws IOException {
        var persons = CsvParser.readPersons(csvFile);
        System.out.println("[L3] CSV gelesen (" + persons.size() + " Datensätze) aus " + csvFile + ":");
        persons.forEach(p -> System.out.println("  " + p));
    }

    // [ ] Erstelle einen Datei-Backup mit Timestamp
    private static void backupWithTimestamp(Path sourceFile, Path backupDir) throws IOException {
        Path backup = BackupService.backupWithTimestamp(sourceFile, backupDir);
        System.out.println("[L3] Backup erstellt: " + backup);
    }

    private static void ensureSampleCsv(Path file) throws IOException {
        if (Files.exists(file)) {
            return;
        }
        Files.createDirectories(file.getParent());
        List<String> lines = List.of(
            "Name;Alter;Stadt",
            "Max;25;Berlin",
            "Anna;30;München",
            "Sam;22;Hamburg"
        );
        Files.write(file, lines, StandardCharsets.UTF_8, StandardOpenOption.CREATE_NEW);
    }

    private static void ensureBackupSource(Path file) throws IOException {
        if (Files.exists(file)) {
            return;
        }
        Files.createDirectories(file.getParent());
        Files.writeString(file, "Backup-Quelle\n", StandardCharsets.UTF_8, StandardOpenOption.CREATE_NEW);
    }
}

