package com.example.app.service;
import com.example.app.model.User;

public interface UserService {
    User login(String username, String password); // return user if ok else null
    User get(String username);
    
    // BỔ SUNG cho Đăng ký:
    boolean register(String email, String password, String username,
                     String fullname, String phone, String confirmPassword);
    boolean existsUsername(String username);
    boolean existsEmail(String email);
    boolean create(User user);
}