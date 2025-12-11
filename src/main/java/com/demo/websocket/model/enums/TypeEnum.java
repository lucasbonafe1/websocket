package com.demo.websocket.model.enums;

public enum TypeEnum {
    SEND_TO_ALL("SEND_TO_ALL"),
    SEND_TO_USER("SEND_TO_USER");

    private final String value;

    TypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
