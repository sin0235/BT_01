package com.example.app.dao;
import com.example.app.model.User;

public interface UserDao {
    User getByUsername(String username);
    User getByEmail(String email);
    boolean create(User user);              // insert; true nếu thành công
    boolean existsUsername(String username);
    boolean existsEmail(String email);
}