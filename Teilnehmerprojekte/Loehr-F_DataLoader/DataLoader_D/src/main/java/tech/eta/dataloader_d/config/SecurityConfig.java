package tech.eta.dataloader_d.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Disabled for this small demo, because the JavaScript login uses
                // the standard Spring Security form-login endpoint.
                .csrf(AbstractHttpConfigurer::disable)
//                .csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**"))
                .authorizeHttpRequests(auth -> auth
                        // DB console should be acessable ...
                        .requestMatchers("/h2-console/**").permitAll()

                        // base page, login page and error page are public obviously
                        .requestMatchers("/", "/login", "/error").permitAll()
                        // we need our scripts and the info "who-is-logged-in"
                        .requestMatchers("/app.js").permitAll()
                        // and finally all our websockets
                        // TODO: I'm uncertain about this. previously I made them authenticated but that was when they didn't work...
                        .requestMatchers("/ws/**").permitAll()
                        //// Secure the WebSocket endpoint at the security filter level
                        //.requestMatchers("/ws/**").authenticated()

                        // Tabs for testing ...
                        .requestMatchers("/dashboard").permitAll()  // dashboard (base page redirects here)
                        .requestMatchers("/simpleList").permitAll() // stale test for in-memory list keeping
                        .requestMatchers("/dummy").authenticated()  // for testing adding logs

                        // Users can be seen by everyone, but created only by ADMIN
                        .requestMatchers("/api/user/current").permitAll()
                        .requestMatchers("/api/user/create").hasRole("ADMIN")

                        // Logs can be created by authenticated users, but seen only by ADMIN
                        .requestMatchers("/api/logs/create").authenticated()
                        .requestMatchers("/api/logs/recent", "/api/logs/all").hasRole("ADMIN")

                        // Console may be viewed by authenticated users, but used only by ADMIN
                        .requestMatchers("/api/cli/list").authenticated()
                        .requestMatchers("/api/cli/call").hasRole("ADMIN")

                        // and everything else is only for authenticated users
                        .anyRequest().authenticated()
                        //.anyRequest().permitAll()
                )

//                .httpBasic(Customizer.withDefaults()) // Basic Auth für einfache API-Tests
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/dashboard", true)
                        .permitAll()
                )


                // The default prevents embedding frames. I must explicitely allow it so the console works.
                .headers(headers -> headers
                        // Option A: Erlaubt das Einbetten von derselben Domain/Herkunft (Empfohlen)
//                        .frameOptions(frameOptions -> frameOptions.sameOrigin())
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)

                        // Option B: Schaltet den Schutz komplett ab (Falls Option A nicht reicht)
                        // .frameOptions(frameOptions -> frameOptions.disable())
                )

                .logout(logout -> logout
                        .logoutSuccessUrl("/dashboard")
                        .permitAll()
                );

        return http.build();
    }

    // Currently we read in users/passwords from in resources/users.json and the passwords contain {noop}.
    // We would use BCrypt or similar and store passwords encoded in a database in a real application.
    @Bean
    PasswordEncoder passwordEncoder() { return PasswordEncoderFactories.createDelegatingPasswordEncoder(); }
}
