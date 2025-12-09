package com.demo.websocket.configuration;

import com.demo.websocket.handler.WsHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@EnableWebSocket
@Configuration
public class WsConfig implements WebSocketConfigurer {
    private final WsHandler wsHandler;

    public WsConfig(WsHandler wsHandler) {
        this.wsHandler = wsHandler;
    }
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(wsHandler, "/ws");
    }
}