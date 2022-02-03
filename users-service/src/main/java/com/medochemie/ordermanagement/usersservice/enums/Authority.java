package com.medochemie.ordermanagement.usersservice.enums;

public enum Authority {
    READ_USER("READ_USER"),
    UPDATE_USER("UPDATE_USER"),
    DELETE_USER("DELETE_USER"),
    ADD_USER("ADD_USER"),
    INACTIVE_USER("INACTIVE_USER");

    private String authority;
    Authority(String authority){
        this.authority = authority;
    }

    private String getAuthority(){
        return this.authority;
    }
}
