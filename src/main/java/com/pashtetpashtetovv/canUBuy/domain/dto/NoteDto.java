package com.pashtetpashtetovv.canUBuy.domain.dto;

public class NoteDto {

    private String title;

    private String description;

    private String ownerLogin;

    public NoteDto() {
    }

    public NoteDto(String title, String description, String ownerLogin) {
        this.title = title;
        this.description = description;
        this.ownerLogin = ownerLogin;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOwnerLogin() {
        return ownerLogin;
    }

    public void setOwnerLogin(String ownerLogin) {
        this.ownerLogin = ownerLogin;
    }

    @Override
    public String toString() {
        return "NoteDto{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", ownerLogin='" + ownerLogin + '\'' +
                '}';
    }

}
