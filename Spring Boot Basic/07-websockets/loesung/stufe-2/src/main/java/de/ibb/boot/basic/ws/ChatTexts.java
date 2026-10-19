package de.ibb.boot.basic.ws;

public final class ChatTexts {
    private ChatTexts() {
    }

    public static String join(String sender) {
        return sender + " ist dem Chat beigetreten!";
    }
}
