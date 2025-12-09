package com.demo.websocket.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class WsHandler implements WebSocketHandler {
    private final Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();

    private static final Logger logger = LoggerFactory.getLogger(WsHandler.class);

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        try {
            sessions.putIfAbsent(session.getId(), session);
            logger.debug("Sessão iniciada {}", session);
        } catch(Exception e) {
            throw new Exception("Ocorreu um erro na tentativa de conexão.", e);
        }
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        try {
            String payload = (String) message.getPayload();
            session.sendMessage(new TextMessage(payload));
        } catch (Exception e) {
            throw new RuntimeException("Ocorreu um erro no processamento de mensagens.", e);
        }
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        logger.error("Ocorreu um erro na sessão {}: ", session.getId(), exception);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        try {
            sessions.remove(session.getId());
        } catch (Exception e) {
            throw new RuntimeException("Ocorreu um erro ao tentar fechar a conexão.", e);
        }
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
