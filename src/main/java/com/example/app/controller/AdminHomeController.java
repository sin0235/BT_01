package com.example.app.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/admin/home")
public class AdminHomeController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        resp.getWriter().println("<!DOCTYPE html>");
        resp.getWriter().println("<html><head><meta charset='UTF-8'><title>Admin Dashboard</title></head>");
        resp.getWriter().println("<body><h1>Chào mừng Admin!</h1>");
        resp.getWriter().println("<p>Bạn đang ở trang quản trị hệ thống.</p>");
        resp.getWriter().println("<a href='" + req.getContextPath() + "/logout'>Đăng xuất</a>");
        resp.getWriter().println("</body></html>");
    }
}