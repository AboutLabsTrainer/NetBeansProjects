package de.ibb.greeting;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("DefaultGreetingService")
class DefaultGreetingServiceTest {

    private GreetingProperties properties;
    private DefaultGreetingService service;

    @BeforeEach
    void setUp() {
        properties = new GreetingProperties();
        service = new DefaultGreetingService(properties);
    }

    @Nested
    @DisplayName("Constructor")
    class ConstructorTests {

        @Test
        @DisplayName("sollte Exception werfen bei null Properties")
        void shouldThrowExceptionForNullProperties() {
            assertThatThrownBy(() -> new DefaultGreetingService(null))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("must not be null");
        }
    }

    @Nested
    @DisplayName("greet() mit Default-Properties")
    class GreetWithDefaults {

        @Test
        @DisplayName("sollte Standard-Begrüßung zurückgeben")
        void shouldReturnDefaultGreeting() {
            String result = service.greet("Petra");
            assertThat(result).isEqualTo("Hello, Petra!");
        }

        @Test
        @DisplayName("sollte Whitespace im Namen trimmen")
        void shouldTrimWhitespaceInName() {
            assertThat(service.greet("  Petra  ")).isEqualTo("Hello, Petra!");
        }
    }

    @Nested
    @DisplayName("greet() mit custom Properties")
    class GreetWithCustomProperties {

        @Test
        @DisplayName("sollte custom Message verwenden")
        void shouldUseCustomMessage() {
            properties.setMessage("Guten Tag");
            assertThat(service.greet("Petra")).isEqualTo("Guten Tag, Petra!");
        }

        @Test
        @DisplayName("sollte Prefix voranstellen")
        void shouldPrependPrefix() {
            properties.setPrefix("[VIP]");
            assertThat(service.greet("Petra")).isEqualTo("[VIP] Hello, Petra!");
        }

        @Test
        @DisplayName("sollte Uppercase anwenden")
        void shouldApplyUppercase() {
            properties.setUppercase(true);
            assertThat(service.greet("Petra")).isEqualTo("HELLO, PETRA!");
        }

        @Test
        @DisplayName("sollte Emoji hinzufügen")
        void shouldAddEmoji() {
            properties.getFormat().setEmoji("👋");
            assertThat(service.greet("Petra")).isEqualTo("👋 Hello, Petra! 👋");
        }

        @Test
        @DisplayName("sollte alle Optionen kombinieren")
        void shouldCombineAllOptions() {
            properties.setMessage("Willkommen");
            properties.setPrefix("[PREMIUM]");
            properties.setUppercase(true);
            properties.getFormat().setEmoji("🎉");

            String result = service.greet("Petra");

            assertThat(result).isEqualTo("🎉 [PREMIUM] WILLKOMMEN, PETRA! 🎉");
        }
    }

    @Nested
    @DisplayName("greet() Edge Cases")
    class GreetEdgeCases {

        @ParameterizedTest
        @NullAndEmptySource
        @ValueSource(strings = {"   ", "\t", "\n"})
        @DisplayName("sollte Exception werfen für ungültige Namen")
        void shouldThrowExceptionForInvalidNames(String invalidName) {
            assertThatThrownBy(() -> service.greet(invalidName))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("Name must not be null or empty");
        }

        @Test
        @DisplayName("sollte Namen mit Sonderzeichen verarbeiten")
        void shouldHandleSpecialCharactersInName() {
            assertThat(service.greet("O'Brien")).isEqualTo("Hello, O'Brien!");
            assertThat(service.greet("名前")).isEqualTo("Hello, 名前!");
        }
    }
}

