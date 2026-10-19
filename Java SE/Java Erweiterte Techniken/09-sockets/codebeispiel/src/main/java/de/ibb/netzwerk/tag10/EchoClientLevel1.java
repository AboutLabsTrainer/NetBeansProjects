package de.ibb.netzwerk.tag10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Gegenstück zum Echo-Server. Liest die Tastatur und zeigt die Antwort, die der Server zurückschickt.
 */
public class EchoClientLevel1 {

    private static final String HOST = "localhost";
    private static final int PORT = 9999;

    public static void main(String[] args) {
        System.out.println("EchoClientLevel1 verbindet sich mit " + HOST + ":" + PORT + " ...");

        try (Socket socket = new Socket(HOST, PORT);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader console = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Verbunden mit Server. Tippe Nachrichten (\"bye\" zum Beenden):");

            String userInput;
            while ((userInput = console.readLine()) != null) {
                out.println(userInput);
                String response = in.readLine();
                if (response == null) {
                    System.out.println("Verbindung vom Server geschlossen.");
                    break;
                }
                System.out.println("Server: " + response);

                if ("bye".equalsIgnoreCase(userInput)) {
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Fehler im Client: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

