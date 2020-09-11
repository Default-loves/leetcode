package com.junyi.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @time: 2020/8/12 18:19
 * @version: 1.0
 * @author: junyi Xu
 * @description:
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Value {

    private int id;
    private String quote;

    public Value(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    @Override
    public String toString() {
        return "Value{" +
                "id=" + id +
                ", quote='" + quote + '\'' +
                '}';
    }
}
