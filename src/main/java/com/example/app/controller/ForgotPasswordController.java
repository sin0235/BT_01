package com.example.app.controller;

import com.example.app.service.UserService;
import com.example.app.service.impl.UserServiceImpl;
import com.example.app.util.Validation;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet(urlPatterns = {"/forgot-password", "/reset-password"})
public class ForgotPasswordController extends HttpServlet {
    private final UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        if ("/forgot-password".equals(path)) {
            req.getRequestDispatcher("/views/forgot-password.jsp").forward(req, resp);
        } else {
            HttpSession s = req.getSession(false);
            if (s == null || s.getAttribute("fp_username") == null || s.getAttribute("fp_email") == null) {
                resp.sendRedirect(req.getContextPath() + "/forgot-password");
                return;
            }
            req.getRequestDispatcher("/views/reset-password.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        if ("/forgot-password".equals(path)) {
            String username = req.getParameter("username");
            String email = req.getParameter("email");
            if (Validation.isBlank(username) || Validation.isBlank(email)) {
                req.setAttribute("alert", "Vui lòng nhập đầy đủ thông tin");
                req.getRequestDispatcher("/views/forgot-password.jsp").forward(req, resp);
                return;
            }
            if (!Validation.isEmail(email)) {
                req.setAttribute("alert", "Email không hợp lệ");
                req.getRequestDispatcher("/views/forgot-password.jsp").forward(req, resp);
                return;
            }
            HttpSession s = req.getSession(true);
            s.setAttribute("fp_username", username);
            s.setAttribute("fp_email", email);
            resp.sendRedirect(req.getContextPath() + "/reset-password");
        } else {
            HttpSession s = req.getSession(false);
            if (s == null) { resp.sendRedirect(req.getContextPath() + "/forgot-password"); return; }
            String username = (String) s.getAttribute("fp_username");
            String email    = (String) s.getAttribute("fp_email");
            if (username == null || email == null) {
                resp.sendRedirect(req.getContextPath() + "/forgot-password"); return;
            }

            String password = req.getParameter("password");
            String confirm  = req.getParameter("confirm");

            if (Validation.isBlank(password) || Validation.isBlank(confirm)) {
                req.setAttribute("alert", "Vui lòng nhập đầy đủ thông tin");
                req.getRequestDispatcher("/views/reset-password.jsp").forward(req, resp);
                return;
            }
            if (!password.equals(confirm)) {
                req.setAttribute("alert", "Mật khẩu và xác nhận mật khẩu không khớp");
                req.getRequestDispatcher("/views/reset-password.jsp").forward(req, resp);
                return;
            }

            boolean ok = userService.resetPassword(username, email, password, confirm);
            if (!ok) {
                req.setAttribute("alert", "Không tìm thấy tài khoản phù hợp (username + email)");
                req.getRequestDispatcher("/views/reset-password.jsp").forward(req, resp);
                return;
            }

            s.removeAttribute("fp_username");
            s.removeAttribute("fp_email");

            s.setAttribute("reset_success", "Đặt lại mật khẩu thành công, vui lòng đăng nhập");
            resp.sendRedirect(req.getContextPath() + "/login");
        }
    }
}