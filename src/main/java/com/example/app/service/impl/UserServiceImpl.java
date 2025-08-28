package com.example.app.service.impl;

import com.example.app.dao.UserDao;
import com.example.app.dao.impl.UserDaoImpl;
import com.example.app.model.User;
import com.example.app.service.UserService;

public class UserServiceImpl implements UserService {
    private final UserDao userDao = new UserDaoImpl();

    @Override
    public User login(String username, String password) {
        User u = userDao.getByUsername(username);
        if (u != null && u.getPassword() != null && u.getPassword().equals(password)) {
            return u;
        }
        return null;
    }

    @Override
    public User get(String username) {
        return userDao.getByUsername(username);
    }
}