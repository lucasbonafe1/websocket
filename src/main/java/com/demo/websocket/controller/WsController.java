package com.demo.websocket.controller;

import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;

@ServerEndpoint("/ws")
public class WsController {
    // Cliente conectou → Registra a sessão
    @OnOpen
    public void onOpen(Session session) {
        // Registrar a sessão do usuário
    }

    // Cliente mandou mensagem → Processa no serviço
    @OnMessage
    public void onMessage(String message, Session session) {
        // Receber a mensagem e encaminhar para o service
    }

    // Cliente desconectou → Limpa recursos
    @OnClose
    public void onClose(Session session) {
        // Remover a sessão do usuário
    }

    // Problemas de rede, queda, formatação, etc.
    @OnError
    public void onError(Session session, Throwable throwable) {
        // Logar/tratar erros
    }
}
