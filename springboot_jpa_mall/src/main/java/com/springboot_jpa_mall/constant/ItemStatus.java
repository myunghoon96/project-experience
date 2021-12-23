package com.springboot_jpa_mall.constant;

public enum ItemStatus {
    SELL("판매"),OUT_OF_STOCK("품절");

    private final String description;

    ItemStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
