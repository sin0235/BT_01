package com.example.app.dao;
import com.example.app.model.User;

public interface UserDao {
    User getByUsername(String username);
}