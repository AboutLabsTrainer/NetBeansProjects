package de.ibb.netzwerk.tag10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Schickt eigene Zeilen an den Chat-Server und gibt parallel aus, was die anderen schreiben.
 */
public class ChatClient {

    private static final String HOST = "localhost";
    private static final int PORT = 9998;

    public static void main(String[] args) {
        System.out.println("ChatClient verbindet sich mit " + HOST + ":" + PORT + " ...");

        try (Socket socket = new Socket(HOST, PORT);
             BufferedReader serverIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter serverOut = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader console = new BufferedReader(new InputStreamReader(System.in))) {

            // Thread zum Lesen vom Server
            Thread readerThread = new Thread(() -> {
                try {
                    String line;
                    while ((line = serverIn.readLine()) != null) {
                        System.out.println(line);
                    }
                } catch (IOException e) {
                    System.out.println("Verbindung zum Server beendet: " + e.getMessage());
                }
            }, "ChatClient-Reader");

            readerThread.setDaemon(true);
            readerThread.start();

            System.out.println("Verbunden mit Chat-Server. Tippe Nachrichten ('/quit' zum Verlassen):");
            String userInput;
            while ((userInput = console.readLine()) != null) {
                serverOut.println(userInput);
                if ("/quit".equalsIgnoreCase(userInput.trim())) {
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Fehler im ChatClient: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

