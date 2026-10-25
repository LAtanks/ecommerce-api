package me.latanks.ecommerce_api.models.enums;

public enum Roles {
    USER(1, "ROLE_USER"),
    ADMIN(2, "ROLE_ADMIN");

    private int code;
    private String description;

    Roles(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
