package com.example.app.dao;

import com.example.app.model.Category;
import java.util.List;

public interface CategoryDao {
    List<Category> findByUser(int userId);
    Category getByIdAndUser(int id, int userId);
    boolean insert(Category c);
    boolean update(Category c);
    boolean delete(int id, int userId);
}