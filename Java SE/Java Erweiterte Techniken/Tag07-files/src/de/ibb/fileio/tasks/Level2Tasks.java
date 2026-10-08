package de.ibb.fileio.tasks;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Comparator;
import java.util.List;
import java.util.Properties;
import java.util.stream.Stream;

/**
 * Fortgeschritten: Properties nach Präfix filtern, einen Verzeichnisbaum durchlaufen und Dateien kopieren.
 */
public final class Level2Tasks {
    private Level2Tasks() {}

    public static void runAll(Path dataDir) throws IOException {
        System.out.println();
        System.out.println("---- Level 2 – Fortgeschritten ----");

        Path configFile = dataDir.resolve("config").resolve("app.properties");
        Path javaScanDir = dataDir.resolve("java-scan");
        Path copySrc = dataDir.resolve("copy-src");
        Path copyDst = dataDir.resolve("copy-dst");

        ensureSampleProperties(configFile);
        ensureSampleJavaTree(javaScanDir);
        ensureSampleCopyFiles(copySrc, copyDst);

        printDbEntries(configFile);
        findJavaFilesRecursively(javaScanDir);
        copyTxtFiles(copySrc, copyDst);
    }

    // [ ] Lade Properties und gib alle db.* Einträge aus
    private static void printDbEntries(Path propertiesFile) throws IOException {
        Properties props = new Properties();
        try (InputStream in = Files.newInputStream(propertiesFile)) {
            props.load(in);
        }

        System.out.println("[L2] db.* Einträge aus " + propertiesFile + ":");
        props.stringPropertyNames().stream()
            .sorted()
            .filter(k -> k.startsWith("db."))
            .forEach(k -> System.out.println("  " + k + "=" + props.getProperty(k)));
    }

    // [ ] Finde alle .java Dateien in einem Verzeichnis (rekursiv)
    private static void findJavaFilesRecursively(Path dir) throws IOException {
        System.out.println("[L2] .java Dateien rekursiv in: " + dir);
        try (Stream<Path> walk = Files.walk(dir)) {
            walk
                .filter(Files::isRegularFile)
                .filter(p -> p.getFileName().toString().endsWith(".java"))
                .sorted(Comparator.comparing(Path::toString))
                .forEach(p -> System.out.println("  " + p));
        }
    }

    // [ ] Kopiere alle .txt Dateien von A nach B
    private static void copyTxtFiles(Path srcDir, Path dstDir) throws IOException {
        Files.createDirectories(dstDir);

        CopyOption[] opts = new CopyOption[] { StandardCopyOption.REPLACE_EXISTING };

        System.out.println("[L2] Kopiere .txt von " + srcDir + " nach " + dstDir);
        try (Stream<Path> list = Files.list(srcDir)) {
            list
                .filter(Files::isRegularFile)
                .filter(p -> p.getFileName().toString().endsWith(".txt"))
                .forEach(p -> {
                    Path target = dstDir.resolve(p.getFileName());
                    try {
                        Files.copy(p, target, opts);
                        System.out.println("  kopiert: " + p.getFileName());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
        } catch (RuntimeException re) {
            if (re.getCause() instanceof IOException io) {
                throw io;
            }
            throw re;
        }
    }

    private static void ensureSampleProperties(Path file) throws IOException {
        if (Files.exists(file)) {
            return;
        }
        Files.createDirectories(file.getParent());
        List<String> lines = List.of(
            "# Datenbank-Konfiguration",
            "db.host=localhost",
            "db.port=5432",
            "db.name=myapp",
            "db.user=admin",
            "db.password=geheim123",
            "",
            "# Anwendungs-Einstellungen",
            "app.name=MeineApp",
            "app.version=1.0.0",
            "app.debug=true",
            "app.maxConnections=10"
        );
        Files.write(file, lines, StandardCharsets.UTF_8);
    }

    private static void ensureSampleJavaTree(Path root) throws IOException {
        if (Files.exists(root) && Files.isDirectory(root)) {
            return;
        }
        Files.createDirectories(root.resolve("a"));
        Files.createDirectories(root.resolve("b").resolve("inner"));

        Files.writeString(root.resolve("Root.java"), "class Root {}\n", StandardCharsets.UTF_8);
        Files.writeString(root.resolve("a").resolve("A1.java"), "class A1 {}\n", StandardCharsets.UTF_8);
        Files.writeString(root.resolve("a").resolve("note.txt"), "not java\n", StandardCharsets.UTF_8);
        Files.writeString(root.resolve("b").resolve("B1.java"), "class B1 {}\n", StandardCharsets.UTF_8);
        Files.writeString(root.resolve("b").resolve("inner").resolve("B2.java"), "class B2 {}\n", StandardCharsets.UTF_8);
    }

    private static void ensureSampleCopyFiles(Path src, Path dst) throws IOException {
        Files.createDirectories(src);
        Files.createDirectories(dst);

        Path f1 = src.resolve("eins.txt");
        Path f2 = src.resolve("zwei.txt");
        Path f3 = src.resolve("ignore.md");

        if (!Files.exists(f1)) {
            Files.writeString(f1, "eins\n", StandardCharsets.UTF_8);
        }
        if (!Files.exists(f2)) {
            Files.writeString(f2, "zwei\n", StandardCharsets.UTF_8);
        }
        if (!Files.exists(f3)) {
            Files.writeString(f3, "ignore\n", StandardCharsets.UTF_8);
        }
    }
}

