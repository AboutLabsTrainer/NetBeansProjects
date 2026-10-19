package de.ibb.fileio;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Locale;
import java.util.Scanner;
import de.ibb.fileio.tasks.Level1Tasks;
import de.ibb.fileio.tasks.Level2Tasks;
import de.ibb.fileio.tasks.Level3Tasks;

/**
 * Einstieg in die Datei-Challenge. Das Menü startet Level 1 bis 3,
 * dieselben Stufen lassen sich auch per Argument aufrufen.
 */
public final class Main {
    private static final Path DATA_DIR = Path.of("data");

    public static void main(String[] args) {
        Locale.setDefault(Locale.GERMANY);

        try {
            if (args != null && args.length > 0) {
                runByArg(args[0]);
                return;
            }
            runMenu();
        } catch (Exception e) {
            System.err.println("[FEHLER] " + e.getClass().getSimpleName() + ": " + e.getMessage());
            e.printStackTrace(System.err);
            System.exit(1);
        }
    }

    private static void runByArg(String arg) throws IOException {
        String cmd = arg == null ? "" : arg.trim().toLowerCase(Locale.ROOT);
        switch (cmd) {
            case "level1" -> Level1Tasks.runAll(DATA_DIR);
            case "level2" -> Level2Tasks.runAll(DATA_DIR);
            case "level3" -> Level3Tasks.runAll(DATA_DIR);
            case "all" -> {
                Level1Tasks.runAll(DATA_DIR);
                Level2Tasks.runAll(DATA_DIR);
                Level3Tasks.runAll(DATA_DIR);
            }
            default -> {
                System.out.println("Unbekanntes Argument: " + arg);
                System.out.println("Erlaubt: level1 | level2 | level3 | all");
            }
        }
    }

    private static void runMenu() throws IOException {
        try (Scanner sc = new Scanner(System.in)) {
            while (true) {
                System.out.println();
                System.out.println("=== Tag 7: File I/O Mini-Challenge (Level 1–3) ===");
                System.out.println("Arbeitsverzeichnis: " + Path.of(".").toAbsolutePath().normalize());
                System.out.println("Datenbasis:         " + DATA_DIR.toAbsolutePath().normalize());
                System.out.println();
                System.out.println("1) Level 1 (Einsteiger) ausführen");
                System.out.println("2) Level 2 (Fortgeschritten) ausführen");
                System.out.println("3) Level 3 (Profi) ausführen");
                System.out.println("4) Alle Level ausführen");
                System.out.println("0) Beenden");
                System.out.print("> ");

                String input = sc.nextLine().trim();
                switch (input) {
                    case "1" -> Level1Tasks.runAll(DATA_DIR);
                    case "2" -> Level2Tasks.runAll(DATA_DIR);
                    case "3" -> Level3Tasks.runAll(DATA_DIR);
                    case "4" -> {
                        Level1Tasks.runAll(DATA_DIR);
                        Level2Tasks.runAll(DATA_DIR);
                        Level3Tasks.runAll(DATA_DIR);
                    }
                    case "0" -> {
                        System.out.println("Bye.");
                        return;
                    }
                    default -> System.out.println("Bitte 0,1,2,3 oder 4 eingeben.");
                }
            }
        }
    }
}

