package tech.eta.dataloader_d.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig
        implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // Messages sent to /topic are broadcast to subscribers.
        registry.enableSimpleBroker("/topic");

        // Client messages sent to /app go to controller methods.
        registry.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {

        // SockJS provides fallback transports for browsers.
        registry.addEndpoint("/ws") // Endpoint that the browser connects to
                // Allow ALL origins to prevent Handshake blocks, which silently kill the "updating feature".
                //  TODO: restrict to few valid origins
                .setAllowedOriginPatterns("*")
                //.withSockJS() // Do I really need this? TODO: check if we may remove it.
        ;
    }
}
