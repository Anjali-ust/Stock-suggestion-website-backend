package com.ust.stock_userdetails.client;

public class FullResponse {
    private String username;
    private Category category;

    public FullResponse() {
    }

    public FullResponse(String username, Category category) {
        this.username = username;
        this.category = category;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
