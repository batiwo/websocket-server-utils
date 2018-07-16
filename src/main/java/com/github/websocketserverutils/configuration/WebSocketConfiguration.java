package com.github.websocketserverutils.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
@EnableScheduling
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer {

    @Value("${websocket.endpoint}")
    private String _websocketEndpoint;

    @Value("${websocket.handler}")
    private String _stompHandler;

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        System.out.println("Using endpoint : " + _websocketEndpoint);
        registry.addEndpoint(_websocketEndpoint).setAllowedOrigins("*");
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker(_stompHandler);
    }
}
