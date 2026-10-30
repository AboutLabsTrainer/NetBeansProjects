package de.ibb.greeting;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("GreetingAutoConfiguration")
class GreetingAutoConfigurationTest {

    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
            .withConfiguration(AutoConfigurations.of(GreetingAutoConfiguration.class));

    @Nested
    @DisplayName("Bean-Erstellung")
    class BeanCreation {

        @Test
        @DisplayName("sollte GreetingService Bean erstellen")
        void shouldCreateGreetingServiceBean() {
            contextRunner.run(context -> {
                assertThat(context).hasSingleBean(GreetingService.class);
                assertThat(context).hasSingleBean(DefaultGreetingService.class);
            });
        }

        @Test
        @DisplayName("sollte GreetingProperties Bean erstellen")
        void shouldCreateGreetingPropertiesBean() {
            contextRunner.run(context -> {
                assertThat(context).hasSingleBean(GreetingProperties.class);
            });
        }

        @Test
        @DisplayName("GreetingService sollte funktionieren")
        void greetingServiceShouldWork() {
            contextRunner.run(context -> {
                GreetingService service = context.getBean(GreetingService.class);
                assertThat(service.greet("Test")).isEqualTo("Hello, Test!");
            });
        }
    }

    @Nested
    @DisplayName("Property Binding")
    class PropertyBinding {

        @Test
        @DisplayName("sollte greeting.message binden")
        void shouldBindMessage() {
            contextRunner
                    .withPropertyValues("greeting.message=Guten Tag")
                    .run(context -> {
                        GreetingService service = context.getBean(GreetingService.class);
                        assertThat(service.greet("Petra")).isEqualTo("Guten Tag, Petra!");
                    });
        }

        @Test
        @DisplayName("sollte greeting.prefix binden")
        void shouldBindPrefix() {
            contextRunner
                    .withPropertyValues("greeting.prefix=[VIP]")
                    .run(context -> {
                        GreetingService service = context.getBean(GreetingService.class);
                        assertThat(service.greet("Petra")).isEqualTo("[VIP] Hello, Petra!");
                    });
        }

        @Test
        @DisplayName("sollte greeting.uppercase binden")
        void shouldBindUppercase() {
            contextRunner
                    .withPropertyValues("greeting.uppercase=true")
                    .run(context -> {
                        GreetingService service = context.getBean(GreetingService.class);
                        assertThat(service.greet("Petra")).isEqualTo("HELLO, PETRA!");
                    });
        }

        @Test
        @DisplayName("sollte nested greeting.format.* Properties binden")
        void shouldBindNestedFormatProperties() {
            contextRunner
                    .withPropertyValues(
                            "greeting.format.emoji=👋",
                            "greeting.format.separator= ->",
                            "greeting.format.suffix=???"
                    )
                    .run(context -> {
                        GreetingService service = context.getBean(GreetingService.class);
                        assertThat(service.greet("Petra")).isEqualTo("👋 Hello->Petra??? 👋");
                    });
        }
    }

    @Nested
    @DisplayName("@ConditionalOnProperty (greeting.enabled)")
    class ConditionalOnProperty {

        @Test
        @DisplayName("sollte Bean erstellen wenn enabled nicht gesetzt ist")
        void shouldCreateBeanWhenEnabledNotSet() {
            contextRunner.run(context -> {
                assertThat(context).hasSingleBean(GreetingService.class);
            });
        }

        @Test
        @DisplayName("sollte Bean erstellen wenn enabled=true")
        void shouldCreateBeanWhenEnabledTrue() {
            contextRunner
                    .withPropertyValues("greeting.enabled=true")
                    .run(context -> {
                        assertThat(context).hasSingleBean(GreetingService.class);
                    });
        }

        @Test
        @DisplayName("sollte KEINEN Bean erstellen wenn enabled=false")
        void shouldNotCreateBeanWhenEnabledFalse() {
            contextRunner
                    .withPropertyValues("greeting.enabled=false")
                    .run(context -> {
                        assertThat(context).doesNotHaveBean(GreetingService.class);
                        assertThat(context).doesNotHaveBean(DefaultGreetingService.class);
                    });
        }
    }

    @Nested
    @DisplayName("@ConditionalOnMissingBean (Überschreibbarkeit)")
    class ConditionalOnMissingBean {

        @Test
        @DisplayName("sollte Custom-Implementierung bevorzugen")
        void shouldPreferCustomImplementation() {
            contextRunner
                    .withUserConfiguration(CustomGreetingServiceConfig.class)
                    .run(context -> {
                        assertThat(context).hasSingleBean(GreetingService.class);
                        assertThat(context).doesNotHaveBean(DefaultGreetingService.class);

                        GreetingService service = context.getBean(GreetingService.class);
                        assertThat(service.greet("Test")).isEqualTo("CUSTOM: Test");
                    });
        }

        @Configuration(proxyBeanMethods = false)
        static class CustomGreetingServiceConfig {

            @Bean
            GreetingService greetingService() {
                return name -> "CUSTOM: " + name;
            }
        }
    }

    @Nested
    @DisplayName("Context Lifecycle")
    class ContextLifecycle {

        @Test
        @DisplayName("sollte Context sauber starten")
        void shouldStartContextCleanly() {
            contextRunner.run(context -> assertThat(context).hasNotFailed());
        }
    }
}

