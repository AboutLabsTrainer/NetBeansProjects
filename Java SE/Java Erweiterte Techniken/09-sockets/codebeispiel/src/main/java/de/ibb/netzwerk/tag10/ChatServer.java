package de.ibb.netzwerk.tag10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Verteilt jede eingehende Zeile an alle verbundenen Clients. Ein Thread-Pool hält die Verbindungen parallel offen.
 * Jeder Client belegt einen Pool-Thread, solange er verbunden ist. Ab dem 21. Client wartet die Verbindung
 * in der Queue des Executors, bis jemand den Chat verlässt.
 */
public class ChatServer {

    private static final int PORT = 9998;
    private static final int THREADS = 20;
    private static final ExecutorService EXECUTOR = Executors.newFixedThreadPool(THREADS);
    private static final List<PrintWriter> CLIENTS = new CopyOnWriteArrayList<>();

    public static void main(String[] args) {
        System.out.println("ChatServer startet auf Port " + PORT + " ...");
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Chat-Server läuft. Warte auf Clients ...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Neuer Client verbunden: " + clientSocket.getInetAddress());
                EXECUTOR.execute(new ChatClientHandler(clientSocket));
            }
        } catch (java.net.BindException e) {
            System.out.println("FEHLER: Port " + PORT + " ist bereits belegt!");
            System.out.println("Mögliche Lösungen:");
            System.out.println("  1. Beende den anderen Prozess, der Port " + PORT + " verwendet");
            System.out.println("  2. Oder ändere den PORT in der Klasse");
            System.out.println("     (Windows: netstat -ano | findstr :" + PORT + " zeigt den Prozess)");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Fehler im ChatServer: " + e.getMessage());
            e.printStackTrace();
        } finally {
            EXECUTOR.shutdown();
        }
    }

    private static class ChatClientHandler implements Runnable {

        private final Socket socket;

        ChatClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            System.out.println("ClientHandler gestartet: " + threadName);

            try (Socket client = socket;
                 BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                 PrintWriter out = new PrintWriter(client.getOutputStream(), true)) {

                CLIENTS.add(out);
                try {
                    broadcast("Client " + threadName + " hat den Chat betreten.", out);
                    out.println("Willkommen im Chat! Tippe Nachrichten, '/quit' zum Verlassen.");

                    String line;
                    while ((line = in.readLine()) != null) {
                        if ("/quit".equalsIgnoreCase(line.trim())) {
                            out.println("Du verlässt den Chat. Tschüss!");
                            break;
                        }
                        String message = "[" + threadName + "] " + line;
                        System.out.println("Broadcast: " + message);
                        broadcast(message, null);
                    }
                } finally {
                    // Ohne remove bliebe der Writer nach dem Trennen in der Liste. PrintWriter meldet
                    // Schreibfehler nicht, also würde jeder Broadcast still an tote Sockets gehen.
                    CLIENTS.remove(out);
                    broadcast("Client " + threadName + " hat den Chat verlassen.", null);
                }
            } catch (IOException e) {
                System.out.println("Fehler in ChatClientHandler (" + threadName + "): " + e.getMessage());
            } finally {
                System.out.println("Client " + threadName + " hat die Verbindung getrennt.");
            }
        }

        private void broadcast(String message, PrintWriter exclude) {
            for (PrintWriter clientOut : CLIENTS) {
                if (clientOut != exclude) {
                    clientOut.println(message);
                }
            }
        }
    }
}

