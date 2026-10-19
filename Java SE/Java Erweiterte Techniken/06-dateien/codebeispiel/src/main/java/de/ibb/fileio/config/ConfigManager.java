package de.ibb.fileio.config;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

/**
 * Hält Anwendungseinstellungen in einer Properties-Datei, füllt fehlende Standardwerte
 * und schreibt Änderungen wieder zurück.
 */
public final class ConfigManager {
    private final Path file;
    private final Properties props;

    private ConfigManager(Path file, Properties props) {
        this.file = file;
        this.props = props;
    }

    public static ConfigManager loadOrCreate(Path file) throws IOException {
        Properties props = new Properties();

        if (Files.exists(file)) {
            try (InputStream in = Files.newInputStream(file)) {
                props.load(in);
            }
        } else {
            Files.createDirectories(file.getParent());
            setDefaults(props);
            store(file, props, "Default Konfiguration (auto-generiert)");
        }

        // ensure defaults exist (non-destructive)
        Properties defaults = new Properties();
        setDefaults(defaults);
        defaults.stringPropertyNames().forEach(k -> props.putIfAbsent(k, defaults.getProperty(k)));

        return new ConfigManager(file, props);
    }

    public Path getFile() {
        return file;
    }

    public Map<String, String> getDbEntries() {
        Map<String, String> out = new TreeMap<>();
        props.stringPropertyNames().stream()
            .filter(k -> k.startsWith("db."))
            .forEach(k -> out.put(k, props.getProperty(k)));
        return out;
    }

    public String getString(String key, String defaultValue) {
        return props.getProperty(key, defaultValue);
    }

    public int getInt(String key, int defaultValue) {
        String raw = props.getProperty(key);
        if (raw == null) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(raw.trim());
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        String raw = props.getProperty(key);
        if (raw == null) {
            return defaultValue;
        }
        return Boolean.parseBoolean(raw.trim());
    }

    public void set(String key, String value) {
        props.setProperty(key, value);
    }

    public void save(String comment) throws IOException {
        store(file, props, comment);
    }

    private static void setDefaults(Properties props) {
        props.setProperty("app.name", "MeineApp");
        props.setProperty("app.version", "1.0.0");
        props.setProperty("app.debug", "true");
        props.setProperty("app.maxConnections", "10");

        props.setProperty("db.host", "localhost");
        props.setProperty("db.port", "5432");
        props.setProperty("db.name", "myapp");
        props.setProperty("db.user", "admin");
        props.setProperty("db.password", "geheim123");

        props.setProperty("last.run", LocalDateTime.now().toString());
    }

    private static void store(Path file, Properties props, String comment) throws IOException {
        try (OutputStream out = Files.newOutputStream(
            file,
            StandardOpenOption.CREATE,
            StandardOpenOption.TRUNCATE_EXISTING
        )) {
            // Properties.store(OutputStream, ...) uses ISO-8859-1; that's fine for ASCII keys/values here.
            props.store(out, comment);
        }

        // additionally keep a UTF-8 readable copy (optional but handy for Umlaute)
        Path utf8Copy = file.resolveSibling(file.getFileName().toString().replace(".properties", "") + ".utf8.properties");
        StringBuilder sb = new StringBuilder();
        sb.append("# ").append(comment == null ? "" : comment).append("\n");
        props.stringPropertyNames().stream().sorted().forEach(k -> sb.append(k).append("=").append(props.getProperty(k)).append("\n"));
        Files.writeString(utf8Copy, sb.toString(), StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }
}

