package com.pollchihuy.connection;

import com.pollchihuy.service.DataCustomerService;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(final MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic");
//        registry.setApplicationDestinationPrefixes("/ws");
    }
    

    @Override
    public void registerStompEndpoints(final StompEndpointRegistry registry) {
//        registry.addEndpoint("/our-websocket")
        registry.addEndpoint("/rey-websocket")
                .setHandshakeHandler(new DataCustomerService())
                .withSockJS();
    }
}