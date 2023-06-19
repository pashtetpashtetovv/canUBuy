package com.pashtetpashtetovv.canUBuy.domain.dto;

public class FriendRequestDTO{

    private String loginFrom;

    private String loginTo;

    public FriendRequestDTO() {
    }

    public FriendRequestDTO(String loginFrom, String loginTo) {
        this.loginFrom = loginFrom;
        this.loginTo = loginTo;
    }

    public String getLoginFrom() {
        return loginFrom;
    }

    public void setLoginFrom(String loginFrom) {
        this.loginFrom = loginFrom;
    }

    public String getLoginTo() {
        return loginTo;
    }

    public void setLoginTo(String loginTo) {
        this.loginTo = loginTo;
    }
}