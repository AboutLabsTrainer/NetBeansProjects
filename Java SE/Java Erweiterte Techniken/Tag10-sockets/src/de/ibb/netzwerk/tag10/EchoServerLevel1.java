package de.ibb.netzwerk.tag10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Bedient genau einen Client nach dem anderen: jede Zeile kommt als Echo zurück, bis {@code bye} kommt.
 */
public class EchoServerLevel1 {

    private static final int PORT = 9999;

    public static void main(String[] args) {
        System.out.println("EchoServerLevel1 startet auf Port " + PORT + " ...");
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server läuft auf Port " + PORT + ". Warte auf Client-Verbindung ...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client verbunden: " + clientSocket.getInetAddress());
                handleClient(clientSocket);
            }
        } catch (java.net.BindException e) {
            System.out.println("FEHLER: Port " + PORT + " ist bereits belegt!");
            System.out.println("Mögliche Lösungen:");
            System.out.println("  1. Beende den anderen Prozess, der Port " + PORT + " verwendet");
            System.out.println("  2. Oder ändere den PORT in der Klasse");
            System.out.println("     (Windows: netstat -ano | findstr :" + PORT + " zeigt den Prozess)");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Fehler im Server: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void handleClient(Socket socket) {
        try (Socket client = socket;
             BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
             PrintWriter out = new PrintWriter(client.getOutputStream(), true)) {

            String line;
            while ((line = in.readLine()) != null) {
                System.out.println("Empfangen: " + line);
                out.println("Echo: " + line);
                if ("bye".equalsIgnoreCase(line)) {
                    System.out.println("Client beendet die Verbindung mit 'bye'.");
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Fehler bei der Client-Kommunikation: " + e.getMessage());
        }
    }
}

