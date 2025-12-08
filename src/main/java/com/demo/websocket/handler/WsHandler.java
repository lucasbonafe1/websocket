package com.demo.websocket.handler;

import org.springframework.web.socket.*;

public class WsHandler implements WebSocketHandler {

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // Cliente conectado
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        // Mensagem recebida
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        // Erros no socket
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // Cliente desconectado
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
