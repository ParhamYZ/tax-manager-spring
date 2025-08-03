package com.spring.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum CustomerType {
    INDIVIDUAL("حقیقی"),
    LEGAL("حقوقی"),
    CIVIL_PARTNERSHIP("مشارکت مدنی"),
    FOREIGN_NATIONAL("اتباع غیر ایرانی"),
    CONSUMER("مصرف کننده");

    private final String label;

    CustomerType(String label) {
        this.label = label;
    }

    @JsonValue // This makes Persian label appear in JSON responses
    public String getLabel() {
        return label;
    }

    @JsonCreator // This lets Spring parse Persian labels in requests
    public static CustomerType fromLabel(String label) {
        for (CustomerType type : values()) {
            if (type.getLabel().equals(label)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown label: " + label);
    }
}
