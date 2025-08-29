package com.example.app.service;
import com.example.app.model.User;

public interface UserService {
    User login(String username, String password);
    User get(String username);
    
    boolean register(String email, String password, String username,
                     String fullname, String phone, String confirmPassword);
    boolean existsUsername(String username);
    boolean existsEmail(String email);
    boolean create(User user);
    boolean resetPassword(String username, String email, String newPassword, String confirmPassword);
}