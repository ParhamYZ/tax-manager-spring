package com.spring.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum StuffIdType {
    PUBLIC_INTERNAL_STUFF_ID("شناسه عمومی تولید داخل (کالا)"),
    PUBLIC_IMPORTED_STUFF_ID("شناسه عمومی وارداتی (کالا)"),
    PUBLIC_SERVICE_ID("شناسه عمومی (خدمات)"),
    PRIVATE_INTERNAL_STUFF_ID("شناسه خصوصی تولید داخل (کالا)"),
    PRIVATE_IMPORTED_STUFF_ID("شناسه خصوصی وارداتی (کالا)"),
    PRIVATE_SERVICE_ID("شناسه خصوصی (خدمات)");

    private final String label;

    StuffIdType(String label) {
        this.label = label;
    }

    @JsonValue // This makes Persian label appear in JSON responses
    public String getLabel() {
        return label;
    }

    @JsonCreator // This lets Spring parse Persian labels in requests
    public static StuffIdType fromLabel(String label) {
        for (StuffIdType type : values()) {
            if (type.getLabel().equals(label)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown label: " + label);
    }
}
