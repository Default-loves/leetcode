package com.junyi.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @time: 2020/8/12 18:18
 * @version: 1.0
 * @author: junyi Xu
 * @description:
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Message {

    private String type;
    private Value value;

    public Message(String type, Value value) {
        this.type = type;
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public Value getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Message{" +
                "type='" + type + '\'' +
                ", value=" + value +
                '}';
    }
}
