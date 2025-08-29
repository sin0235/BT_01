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
        // 1) Validate rỗng
        if (Validation.isBlank(email) || Validation.isBlank(password) ||
            Validation.isBlank(username) || Validation.isBlank(fullname) ||
            Validation.isBlank(confirmPassword)) {
            return false; // Controller sẽ set "Vui lòng nhập đầy đủ thông tin"
        }

        // 2) Validate email
        if (!Validation.isEmail(email)) {
            return false; // "Email không hợp lệ"
        }

        // 3) Khớp mật khẩu
        if (!password.equals(confirmPassword)) {
            return false; // "Mật khẩu và xác nhận mật khẩu không khớp"
        }

        // 4) Tồn tại tài khoản/email?
        if (userDao.existsUsername(username)) {
            return false; // "Tài khoản đã tồn tại"
        }
        if (userDao.existsEmail(email)) {
            return false; // "Email đã được sử dụng"
        }

        // 5) Tạo user
        User u = new User();
        u.setEmail(email);
        u.setUsername(username);
        u.setFullname(fullname);
        u.setPassword(password); // DEMO: plain; thực tế BCrypt
        u.setAvatar(null);
        u.setRoleid(5);          // yêu cầu giữ 5
        u.setPhone(phone);

        return userDao.create(u);
    }
}