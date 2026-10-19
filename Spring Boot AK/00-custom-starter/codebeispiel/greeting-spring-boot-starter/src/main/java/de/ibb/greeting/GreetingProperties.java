package de.ibb.greeting;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration Properties für den Greeting Starter.
 *
 * <p>Alle Properties mit dem Prefix {@code greeting} werden automatisch
 * an diese Klasse gebunden.</p>
 */
@ConfigurationProperties(prefix = "greeting")
public class GreetingProperties {

    /**
     * Die Begrüßungs-Message.
     * Standard: "Hello"
     */
    private String message = "Hello";

    /**
     * Soll die Ausgabe in Großbuchstaben sein?
     * Standard: false
     */
    private boolean uppercase = false;

    /**
     * Optionaler Prefix vor der Message.
     */
    private String prefix = "";

    /**
     * Aktiviert/deaktiviert den Greeting Service.
     * Standard: true
     *
     * <p>Wird von der Auto-Configuration über {@code @ConditionalOnProperty}
     * ausgewertet – nicht vom {@link GreetingService} selbst.</p>
     */
    private boolean enabled = true;

    /**
     * Format-Optionen für erweiterte Anpassung.
     */
    private Format format = new Format();

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isUppercase() {
        return uppercase;
    }

    public void setUppercase(boolean uppercase) {
        this.uppercase = uppercase;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Format getFormat() {
        return format;
    }

    public void setFormat(Format format) {
        this.format = format;
    }

    /**
     * Nested Configuration für Format-Optionen.
     * Konfigurierbar über {@code greeting.format.*}
     */
    public static class Format {

        /**
         * Optionales Emoji, das der Begrüßung hinzugefügt wird.
         */
        private String emoji = "";

        /**
         * Separator zwischen Message und Name.
         * Standard: ", "
         */
        private String separator = ", ";

        /**
         * Suffix nach dem Namen.
         * Standard: "!"
         */
        private String suffix = "!";

        public String getEmoji() {
            return emoji;
        }

        public void setEmoji(String emoji) {
            this.emoji = emoji;
        }

        public String getSeparator() {
            return separator;
        }

        public void setSeparator(String separator) {
            this.separator = separator;
        }

        public String getSuffix() {
            return suffix;
        }

        public void setSuffix(String suffix) {
            this.suffix = suffix;
        }
    }
}

