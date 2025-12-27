package com.demo.websocket.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.Map;

@Service
public class MessageService {
    private static final Logger logger = LoggerFactory.getLogger(MessageService.class);

    public void broadcast(WebSocketSession session, Map<String, WebSocketSession> connectedSessions, String payload){
        connectedSessions.values().forEach(f -> {
            try {
                if (f.getId().equals(session.getId()) || !f.isOpen()) return;

                f.sendMessage(new TextMessage(payload));
            } catch (IOException e) {
                logger.error("Ocorreu um erro ao realizar um broadcast para a sessão {}: ", session.getId(), e);

                String userId = (String) session.getAttributes().get("userId");
                connectedSessions.remove(userId); // remove sessão problemática
            }
        });
    }
}
