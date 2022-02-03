package com.medochemie.ordermanagement.usersservice.enums;

public enum Role {
    ADMIN("ADMIN"),
    USER("USER"),
    SR_MANAGER("SR_MANAGER"),
    DEVELOPER("DEVELOPER"),
    ;

private final String role;

    Role(String role) {
        this.role = role;
    }
    public String getRole(){
        return this.role;
    }
}
