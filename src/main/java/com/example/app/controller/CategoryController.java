package com.example.app.controller;

import com.example.app.model.User;
import com.example.app.service.CategoryService;
import com.example.app.service.impl.CategoryServiceImpl;
import com.example.app.util.Constant;
import com.example.app.util.Validation;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet(urlPatterns = {
    "/category/list", "/category/create", "/category/edit", "/category/delete"
})
public class CategoryController extends HttpServlet {
    private final CategoryService categoryService = new CategoryServiceImpl();

    private User requireLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession s = req.getSession(false);
        if (s == null || s.getAttribute(Constant.SESSION_ACCOUNT) == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return null;
        }
        return (User) s.getAttribute(Constant.SESSION_ACCOUNT);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        User me = requireLogin(req, resp);
        if (me == null) return;

        switch (path) {
            case "/category/list" -> {
                req.setAttribute("items", categoryService.findByUser(me.getId()));
                req.getRequestDispatcher("/views/category-list.jsp").forward(req, resp);
            }
            case "/category/create" -> {
                req.getRequestDispatcher("/views/category-form.jsp").forward(req, resp);
            }
            case "/category/edit" -> {
                String sid = req.getParameter("id");
                int id = (Validation.isBlank(sid)) ? -1 : Integer.parseInt(sid);
                var item = categoryService.getByIdAndUser(id, me.getId());
                if (item == null) {
                    resp.sendRedirect(req.getContextPath() + "/category/list");
                    return;
                }
                req.setAttribute("item", item);
                req.getRequestDispatcher("/views/category-form.jsp").forward(req, resp);
            }
            default -> resp.sendError(404);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        User me = requireLogin(req, resp);
        if (me == null) return;

        switch (path) {
            case "/category/create" -> {
                String name = req.getParameter("name");
                String description = req.getParameter("description");
                if (categoryService.create(me.getId(), name, description)) {
                    resp.sendRedirect(req.getContextPath() + "/category/list");
                } else {
                    req.setAttribute("alert", "Tên danh mục không được rỗng");
                    req.getRequestDispatcher("/views/category-form.jsp").forward(req, resp);
                }
            }
            case "/category/edit" -> {
                int id = Integer.parseInt(req.getParameter("id"));
                String name = req.getParameter("name");
                String description = req.getParameter("description");
                if (categoryService.update(id, me.getId(), name, description)) {
                    resp.sendRedirect(req.getContextPath() + "/category/list");
                } else {
                    req.setAttribute("alert", "Không thể cập nhật (không thuộc sở hữu hoặc tên rỗng)");
                    req.setAttribute("item", categoryService.getByIdAndUser(id, me.getId()));
                    req.getRequestDispatcher("/views/category-form.jsp").forward(req, resp);
                }
            }
            case "/category/delete" -> {
                int id = Integer.parseInt(req.getParameter("id"));
                categoryService.delete(id, me.getId());
                resp.sendRedirect(req.getContextPath() + "/category/list");
            }
            default -> resp.sendError(404);
        }
    }
}