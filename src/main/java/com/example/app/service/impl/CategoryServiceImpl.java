package com.example.app.service.impl;

import com.example.app.dao.CategoryDao;
import com.example.app.dao.impl.CategoryDaoImpl;
import com.example.app.model.Category;
import com.example.app.service.CategoryService;
import com.example.app.util.Validation;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    private final CategoryDao categoryDao = new CategoryDaoImpl();

    @Override 
    public List<Category> findByUser(int userId) { 
        return categoryDao.findByUser(userId); 
    }

    @Override 
    public Category getByIdAndUser(int id, int userId) { 
        return categoryDao.getByIdAndUser(id, userId); 
    }

    @Override
    public boolean create(int userId, String name, String description) {
        if (Validation.isBlank(name)) return false;
        Category c = new Category();
        c.setUserId(userId);
        c.setName(name.trim());
        c.setDescription(Validation.isBlank(description) ? null : description.trim());
        return categoryDao.insert(c);
    }

    @Override
    public boolean update(int id, int userId, String name, String description) {
        if (Validation.isBlank(name)) return false;
        Category c = new Category();
        c.setId(id);
        c.setUserId(userId);
        c.setName(name.trim());
        c.setDescription(Validation.isBlank(description) ? null : description.trim());
        return categoryDao.update(c);
    }

    @Override
    public boolean delete(int id, int userId) {
        return categoryDao.delete(id, userId);
    }
}