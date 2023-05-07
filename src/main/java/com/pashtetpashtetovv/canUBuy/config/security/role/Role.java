package com.pashtetpashtetovv.canUBuy.config.security.role;

public enum Role {

    USER("USER"),
    ADMIN("ADMIN");

    private String code;

    Role(String code){
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
