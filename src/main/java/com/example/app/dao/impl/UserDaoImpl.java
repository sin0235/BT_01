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

    @Override
    public User getByEmail(String email) {
        String sql = "SELECT * FROM User WHERE email = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, email);
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

    @Override
    public boolean existsUsername(String username) {
        String sql = "SELECT 1 FROM User WHERE username = ? LIMIT 1";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean existsEmail(String email) {
        String sql = "SELECT 1 FROM User WHERE email = ? LIMIT 1";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean create(User user) {
        String sql = "INSERT INTO User(email, username, fullname, password, avatar, roleid, phone, createddate) " +
                     "VALUES(?,?,?,?,?,?,?,CURRENT_TIMESTAMP)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getUsername());
            ps.setString(3, user.getFullname());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getAvatar());
            ps.setInt(6, user.getRoleid());
            ps.setString(7, user.getPhone());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updatePasswordByUsernameEmail(String username, String email, String newPassword) {
        String sql = "UPDATE User SET password = ? WHERE username = ? AND email = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, newPassword);
            ps.setString(2, username);
            ps.setString(3, email);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}