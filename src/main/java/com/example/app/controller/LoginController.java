package com.example.app.controller;

import com.example.app.model.User;
import com.example.app.service.UserService;
import com.example.app.service.impl.UserServiceImpl;
import com.example.app.util.Constant;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    private final UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute(Constant.SESSION_ACCOUNT) != null) {
            resp.sendRedirect(req.getContextPath() + "/waiting");
            return;
        }
        
        // Kiểm tra thông báo thành công từ đăng ký
        if (session != null) {
            Object msg = session.getAttribute("register_success");
            if (msg != null) {
                req.setAttribute("success", msg.toString());
                session.removeAttribute("register_success");
            }
        }
        
        // auto-login by cookie
        String cookieUsername = getCookieValue(req, Constant.COOKIE_REMEMBER);
        if (cookieUsername != null && !cookieUsername.isBlank()) {
            User u = userService.get(cookieUsername);
            if (u != null) {
                req.getSession(true).setAttribute(Constant.SESSION_ACCOUNT, u);
                resp.sendRedirect(req.getContextPath() + "/waiting");
                return;
            }
        }
        req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String remember = req.getParameter("remember"); // "on" if checked

        if (isBlank(username) || isBlank(password)) {
            req.setAttribute("alert", "Tài khoản hoặc mật khẩu không được rỗng");
            req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
            return;
        }

        User u = userService.login(username, password);
        if (u == null) {
            req.setAttribute("alert", "Tài khoản hoặc mật khẩu không đúng");
            req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
            return;
        }

        // success
        HttpSession session = req.getSession(true);
        session.setAttribute(Constant.SESSION_ACCOUNT, u);

        if ("on".equalsIgnoreCase(remember)) {
            saveRememberMe(resp, u.getUsername());
        }
        resp.sendRedirect(req.getContextPath() + "/waiting");
    }

    private void saveRememberMe(HttpServletResponse resp, String username) {
        Cookie c = new Cookie(Constant.COOKIE_REMEMBER, username);
        c.setPath("/");             // toàn app
        c.setMaxAge(30 * 60);       // 30 phút
        c.setHttpOnly(true);
        resp.addCookie(c);
    }

    private String getCookieValue(HttpServletRequest req, String name) {
        Cookie[] cookies = req.getCookies();
        if (cookies == null) return null;
        for (Cookie c : cookies) if (name.equals(c.getName())) return c.getValue();
        return null;
    }

    private boolean isBlank(String s) { return s == null || s.trim().isEmpty(); }
}