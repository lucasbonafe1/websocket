package com.demo.websocket.handler;

import com.demo.websocket.model.dtos.MessageDTO;
import com.demo.websocket.model.enums.TypeEnum;
import com.demo.websocket.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;
import tools.jackson.databind.ObjectMapper;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class WsHandler implements WebSocketHandler {
    private final Map<String, WebSocketSession> connectedSessions = new ConcurrentHashMap<>();

    private static final Logger logger = LoggerFactory.getLogger(WsHandler.class);

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MessageService messageService;
    
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        try {
            String token = (String) session.getAttributes().get("token");
            System.out.println("Token que acabou de conectar" + token);
            connectedSessions.putIfAbsent(token, session);
            logger.debug("Sessão iniciada {}", session);
        } catch(Exception e) {
            throw new Exception("Ocorreu um erro na tentativa de conexão.", e);
        }
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        try {
            MessageDTO dto = objectMapper.readValue((String) message.getPayload(), MessageDTO.class);

            if (dto.getType().equals(TypeEnum.SEND_TO_ALL)){
                messageService.broadcast(session, connectedSessions, dto.getContent());
            } else {
                WebSocketSession sessionReceiver = connectedSessions.get(dto.getTo());
                sessionReceiver.sendMessage(new TextMessage(dto.getContent()));
            }

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
            String token = (String) session.getAttributes().get("token");
            connectedSessions.remove(token);
        } catch (Exception e) {
            throw new RuntimeException("Ocorreu um erro ao tentar fechar a conexão.", e);
        }
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
