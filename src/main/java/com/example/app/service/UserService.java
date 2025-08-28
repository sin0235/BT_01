package com.example.app.service;
import com.example.app.model.User;

public interface UserService {
    User login(String username, String password); // return user if ok else null
    User get(String username);
}