package com.springboot_jpa_mall.constant;

public enum ItemStatus {
    SELL("ํ๋งค"),OUT_OF_STOCK("ํ์ ");

    private final String description;

    ItemStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
