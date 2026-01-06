package com.demo.websocket.model.dtos;

import com.demo.websocket.model.enums.TypeEnum;

public class MessageDTO {
    public TypeEnum type;
    public String content;
    public String to;

    public TypeEnum getType() {
        return type;
    }

    public void setType(TypeEnum type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
