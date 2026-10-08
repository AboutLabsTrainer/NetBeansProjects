package de.ibb.greeting;

/**
 * Standard-Implementierung des {@link GreetingService}.
 *
 * <p>Baut die Begrüßung aus den Werten in {@link GreetingProperties} zusammen.
 * Die Reihenfolge ist fest: Prefix, Message, Separator, Name, Suffix,
 * optional Großschreibung, danach optional Emoji (damit das Emoji nicht
 * mit umgewandelt wird).</p>
 */
public class DefaultGreetingService implements GreetingService {

    private final GreetingProperties properties;

    /**
     * @param properties Konfiguration für Message, Format usw.; darf nicht {@code null} sein
     * @throws IllegalArgumentException wenn {@code properties} null ist
     */
    public DefaultGreetingService(GreetingProperties properties) {
        if (properties == null) {
            throw new IllegalArgumentException("GreetingProperties must not be null");
        }
        this.properties = properties;
    }

    @Override
    public String greet(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name must not be null or empty");
        }

        String trimmedName = name.trim();
        StringBuilder greeting = new StringBuilder();

        // 1) Optionaler Prefix (mit Leerzeichen als Trenner)
        String prefix = properties.getPrefix();
        if (prefix != null && !prefix.isEmpty()) {
            greeting.append(prefix).append(" ");
        }

        // 2) Message + Separator + Name + Suffix
        greeting.append(properties.getMessage());
        greeting.append(properties.getFormat().getSeparator());
        greeting.append(trimmedName);
        greeting.append(properties.getFormat().getSuffix());

        String result = greeting.toString();

        // 3) Optional Großschreibung (vor dem Emoji, damit das Emoji unverändert bleibt)
        if (properties.isUppercase()) {
            result = result.toUpperCase();
        }

        // 4) Optional Emoji um die fertige Begrüßung legen
        String emoji = properties.getFormat().getEmoji();
        if (emoji != null && !emoji.isEmpty()) {
            result = emoji + " " + result + " " + emoji;
        }

        return result;
    }

    public GreetingProperties getProperties() {
        return properties;
    }
}
