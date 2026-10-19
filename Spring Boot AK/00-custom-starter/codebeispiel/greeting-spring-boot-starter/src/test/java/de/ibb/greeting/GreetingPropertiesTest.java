package de.ibb.greeting;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("GreetingProperties")
class GreetingPropertiesTest {

    @Nested
    @DisplayName("Default Values")
    class DefaultValues {

        @Test
        @DisplayName("message sollte 'Hello' sein")
        void messageShouldDefaultToHello() {
            GreetingProperties props = new GreetingProperties();
            assertThat(props.getMessage()).isEqualTo("Hello");
        }

        @Test
        @DisplayName("uppercase sollte false sein")
        void uppercaseShouldDefaultToFalse() {
            GreetingProperties props = new GreetingProperties();
            assertThat(props.isUppercase()).isFalse();
        }

        @Test
        @DisplayName("prefix sollte leer sein")
        void prefixShouldDefaultToEmpty() {
            GreetingProperties props = new GreetingProperties();
            assertThat(props.getPrefix()).isEmpty();
        }

        @Test
        @DisplayName("enabled sollte true sein")
        void enabledShouldDefaultToTrue() {
            GreetingProperties props = new GreetingProperties();
            assertThat(props.isEnabled()).isTrue();
        }

        @Test
        @DisplayName("format sollte nicht null sein")
        void formatShouldNotBeNull() {
            GreetingProperties props = new GreetingProperties();
            assertThat(props.getFormat()).isNotNull();
        }
    }

    @Nested
    @DisplayName("Format Default Values")
    class FormatDefaultValues {

        @Test
        @DisplayName("separator sollte ', ' sein")
        void separatorShouldDefaultToCommaSpace() {
            GreetingProperties.Format format = new GreetingProperties.Format();
            assertThat(format.getSeparator()).isEqualTo(", ");
        }

        @Test
        @DisplayName("suffix sollte '!' sein")
        void suffixShouldDefaultToExclamation() {
            GreetingProperties.Format format = new GreetingProperties.Format();
            assertThat(format.getSuffix()).isEqualTo("!");
        }

        @Test
        @DisplayName("emoji sollte leer sein")
        void emojiShouldDefaultToEmpty() {
            GreetingProperties.Format format = new GreetingProperties.Format();
            assertThat(format.getEmoji()).isEmpty();
        }
    }

    @Nested
    @DisplayName("Setter & Getter")
    class SettersAndGetters {

        @Test
        @DisplayName("setMessage sollte Wert speichern")
        void setMessageShouldStoreValue() {
            GreetingProperties props = new GreetingProperties();
            props.setMessage("Guten Tag");
            assertThat(props.getMessage()).isEqualTo("Guten Tag");
        }

        @Test
        @DisplayName("Format-Setter sollte Werte speichern")
        void formatSettersShouldStoreValues() {
            GreetingProperties.Format format = new GreetingProperties.Format();
            format.setEmoji("👋");
            format.setSeparator(" -> ");

            assertThat(format.getEmoji()).isEqualTo("👋");
            assertThat(format.getSeparator()).isEqualTo(" -> ");
        }
    }
}

