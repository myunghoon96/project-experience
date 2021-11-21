package com.my_shop.constant;

public enum Role {
    USER("고객"), ADMIN("판매자");

    private final String description;

    Role(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
