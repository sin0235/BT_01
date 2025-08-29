package com.example.app.model;

import java.time.LocalDateTime;

public class Category {
    private int id;
    private int userId;
    private String name;
    private String description;
    private LocalDateTime createddate;

    public Category() {}

    public Category(int id, int userId, String name, String description, LocalDateTime createddate) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.description = description;
        this.createddate = createddate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreateddate() {
        return createddate;
    }

    public void setCreateddate(LocalDateTime createddate) {
        this.createddate = createddate;
    }
}