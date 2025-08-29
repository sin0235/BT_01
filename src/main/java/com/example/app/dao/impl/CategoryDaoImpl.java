package com.example.app.dao.impl;

import com.example.app.dao.CategoryDao;
import com.example.app.model.Category;
import com.example.app.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDaoImpl implements CategoryDao {
    @Override
    public List<Category> findByUser(int userId) {
        String sql = "SELECT * FROM Category WHERE user_id = ? ORDER BY createddate DESC";
        List<Category> list = new ArrayList<>();
        try (var con = DBConnection.getConnection();
             var ps = con.prepareStatement(sql)) {
            ps.setInt(1, userId);
            try (var rs = ps.executeQuery()) {
                while (rs.next()) {
                    Category c = map(rs);
                    list.add(c);
                }
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    @Override
    public Category getByIdAndUser(int id, int userId) {
        String sql = "SELECT * FROM Category WHERE id=? AND user_id=?";
        try (var con = DBConnection.getConnection();
             var ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.setInt(2, userId);
            try (var rs = ps.executeQuery()) {
                if (rs.next()) return map(rs);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return null;
    }

    @Override
    public boolean insert(Category c) {
        String sql = "INSERT INTO Category(user_id, name, description, createddate) VALUES(?,?,?,CURRENT_TIMESTAMP)";
        try (var con = DBConnection.getConnection();
             var ps = con.prepareStatement(sql)) {
            ps.setInt(1, c.getUserId());
            ps.setString(2, c.getName());
            ps.setString(3, c.getDescription());
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    @Override
    public boolean update(Category c) {
        String sql = "UPDATE Category SET name=?, description=? WHERE id=? AND user_id=?";
        try (var con = DBConnection.getConnection();
             var ps = con.prepareStatement(sql)) {
            ps.setString(1, c.getName());
            ps.setString(2, c.getDescription());
            ps.setInt(3, c.getId());
            ps.setInt(4, c.getUserId());
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    @Override
    public boolean delete(int id, int userId) {
        String sql = "DELETE FROM Category WHERE id=? AND user_id=?";
        try (var con = DBConnection.getConnection();
             var ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.setInt(2, userId);
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    private Category map(ResultSet rs) throws SQLException {
        Category c = new Category();
        c.setId(rs.getInt("id"));
        c.setUserId(rs.getInt("user_id"));
        c.setName(rs.getString("name"));
        c.setDescription(rs.getString("description"));
        Timestamp ts = rs.getTimestamp("createddate");
        if (ts != null) c.setCreateddate(ts.toLocalDateTime());
        return c;
    }
}