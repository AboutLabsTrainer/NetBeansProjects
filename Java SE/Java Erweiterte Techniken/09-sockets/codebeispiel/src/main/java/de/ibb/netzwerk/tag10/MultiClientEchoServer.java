package de.ibb.netzwerk.tag10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Nimmt mehrere Clients gleichzeitig an. Jede Verbindung läuft in einem Thread aus einem festen Pool.
 */
public class MultiClientEchoServer {

    private static final int PORT = 9999;
    private static final int THREADS = 10;
    private static final ExecutorService EXECUTOR = Executors.newFixedThreadPool(THREADS);

    public static void main(String[] args) {
        System.out.println("MultiClientEchoServer startet auf Port " + PORT + " ...");
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server läuft auf Port " + PORT + ". Warte auf Clients ...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client verbunden: " + clientSocket.getInetAddress());
                EXECUTOR.execute(new ClientHandler(clientSocket));
            }
        } catch (java.net.BindException e) {
            System.out.println("FEHLER: Port " + PORT + " ist bereits belegt!");
            System.out.println("Mögliche Lösungen:");
            System.out.println("  1. Beende den anderen Prozess, der Port " + PORT + " verwendet");
            System.out.println("  2. Oder ändere den PORT in der Klasse");
            System.out.println("     (Windows: netstat -ano | findstr :" + PORT + " zeigt den Prozess)");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Fehler im MultiClientEchoServer: " + e.getMessage());
            e.printStackTrace();
        } finally {
            EXECUTOR.shutdown();
        }
    }

    private static class ClientHandler implements Runnable {

        private final Socket socket;

        ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            try (Socket client = socket;
                 BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                 PrintWriter out = new PrintWriter(client.getOutputStream(), true)) {

                out.println("Willkommen! Deine ID: " + threadName);

                String line;
                while ((line = in.readLine()) != null) {
                    System.out.println("[" + threadName + "] " + line);
                    out.println("Echo: " + line);
                    if ("bye".equalsIgnoreCase(line)) {
                        break;
                    }
                }
            } catch (IOException e) {
                System.out.println("Fehler in ClientHandler (" + threadName + "): " + e.getMessage());
            }
        }
    }
}

