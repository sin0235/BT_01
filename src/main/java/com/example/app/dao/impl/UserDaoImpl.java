package com.example.app.dao.impl;

import com.example.app.dao.UserDao;
import com.example.app.model.User;
import com.example.app.util.DBConnection;

import java.sql.*;

public class UserDaoImpl implements UserDao {
    @Override
    public User getByUsername(String username) {
        String sql = "SELECT * FROM User WHERE username = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    User u = new User();
                    u.setId(rs.getInt("id"));
                    u.setEmail(rs.getString("email"));
                    u.setUsername(rs.getString("username"));
                    u.setFullname(rs.getString("fullname"));
                    u.setPassword(rs.getString("password"));
                    u.setAvatar(rs.getString("avatar"));
                    u.setRoleid(rs.getInt("roleid"));
                    u.setPhone(rs.getString("phone"));
                    Timestamp ts = rs.getTimestamp("createddate");
                    if (ts != null) u.setCreateddate(ts.toLocalDateTime());
                    return u;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}