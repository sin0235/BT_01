package com.example.app.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/manager/home")
public class ManagerHomeController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        resp.getWriter().println("<!DOCTYPE html>");
        resp.getWriter().println("<html><head><meta charset='UTF-8'><title>Manager Dashboard</title></head>");
        resp.getWriter().println("<body><h1>Chào mừng Manager!</h1>");
        resp.getWriter().println("<p>Bạn đang ở trang quản lý cửa hàng.</p>");
        resp.getWriter().println("<a href='" + req.getContextPath() + "/logout'>Đăng xuất</a>");
        resp.getWriter().println("</body></html>");
    }
}