package com.drools.sample.model;

import java.util.HashMap;
import java.util.Map;

public enum Operator {
    NOT_EQUAL_TO("NOT_EQUAL_TO"),
    EQUAL_TO("EQUAL_TO"),
    GREATER_THAN("GREATER_THAN"),
    LESS_THAN("LESS_THAN"),
    GREATER_THAN_OR_EQUAL_TO("GREATER_THAN_OR_EQUAL_TO"),
    LESS_THAN_OR_EQUAL_TO("LESS_THAN_OR_EQUAL_TO"),
    AND("AND"),
    OR("OR");
    private final String value;
    private static Map<String, Operator> constants = new HashMap<String, Operator>();

    static {
        for (Operator c : values()) {
            constants.put(c.value, c);
        }
    }

    Operator(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }

    public static Operator fromValue(String value) {
        Operator constant = constants.get(value);
        if (constant == null) {
            throw new IllegalArgumentException(value);
        } else {
            return constant;
        }
    }
}