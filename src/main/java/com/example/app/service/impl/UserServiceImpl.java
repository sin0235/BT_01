package com.example.app.service.impl;

import com.example.app.dao.UserDao;
import com.example.app.dao.impl.UserDaoImpl;
import com.example.app.model.User;
import com.example.app.service.UserService;
import com.example.app.util.Validation;

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

    @Override
    public boolean existsUsername(String username){ 
        return userDao.existsUsername(username); 
    }

    @Override
    public boolean existsEmail(String email){ 
        return userDao.existsEmail(email); 
    }

    @Override
    public boolean create(User user){ 
        return userDao.create(user); 
    }

    @Override
    public boolean register(String email, String password, String username,
                            String fullname, String phone, String confirmPassword) {
        if (Validation.isBlank(email) || Validation.isBlank(password) ||
            Validation.isBlank(username) || Validation.isBlank(fullname) ||
            Validation.isBlank(confirmPassword)) {
            return false;
        }

        if (!Validation.isEmail(email)) {
            return false;
        }

        if (!password.equals(confirmPassword)) {
            return false;
        }

        if (userDao.existsUsername(username)) {
            return false;
        }
        if (userDao.existsEmail(email)) {
            return false;
        }

        User u = new User();
        u.setEmail(email);
        u.setUsername(username);
        u.setFullname(fullname);
        u.setPassword(password);
        u.setAvatar(null);
        u.setRoleid(5);
        u.setPhone(phone);

        return userDao.create(u);
    }

    @Override
    public boolean resetPassword(String username, String email, String newPassword, String confirmPassword) {
        if (Validation.isBlank(username) || Validation.isBlank(email) ||
            Validation.isBlank(newPassword) || Validation.isBlank(confirmPassword)) {
            return false;
        }
        if (!Validation.isEmail(email)) {
            return false;
        }
        if (!newPassword.equals(confirmPassword)) {
            return false;
        }
        return userDao.updatePasswordByUsernameEmail(username, email, newPassword);
    }
}