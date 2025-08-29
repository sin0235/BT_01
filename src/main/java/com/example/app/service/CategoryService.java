package com.example.app.service;

import com.example.app.model.Category;
import java.util.List;

public interface CategoryService {
    List<Category> findByUser(int userId);
    Category getByIdAndUser(int id, int userId);
    boolean create(int userId, String name, String description);
    boolean update(int id, int userId, String name, String description);
    boolean delete(int id, int userId);
}