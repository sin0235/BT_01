package com.example.app.controller;

import com.example.app.service.UserService;
import com.example.app.service.impl.UserServiceImpl;
import com.example.app.util.Validation;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/register")
public class RegisterController extends HttpServlet {
  private final UserService userService = new UserServiceImpl();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String email    = req.getParameter("email");
    String username = req.getParameter("username");
    String fullname = req.getParameter("fullname");
    String password = req.getParameter("password");
    String confirm  = req.getParameter("confirm");
    String phone    = req.getParameter("phone");

    // Tự set thông điệp cụ thể theo từng lỗi để đúng chuỗi yêu cầu:
    if (Validation.isBlank(email) || Validation.isBlank(username) ||
        Validation.isBlank(fullname) || Validation.isBlank(password) ||
        Validation.isBlank(confirm)) {
      req.setAttribute("alert", "Vui lòng nhập đầy đủ thông tin");
      req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
      return;
    }
    if (!Validation.isEmail(email)) {
      req.setAttribute("alert", "Email không hợp lệ");
      req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
      return;
    }
    if (!password.equals(confirm)) {
      req.setAttribute("alert", "Mật khẩu và xác nhận mật khẩu không khớp");
      req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
      return;
    }
    if (userService.existsUsername(username)) {
      req.setAttribute("alert", "Tài khoản đã tồn tại");
      req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
      return;
    }
    if (userService.existsEmail(email)) {
      req.setAttribute("alert", "Email đã được sử dụng");
      req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
      return;
    }

    boolean ok = userService.register(email, password, username, fullname, phone, confirm);
    if (!ok) {
      // fallback an toàn (nếu có lỗi DB)
      req.setAttribute("alert", "Vui lòng nhập đầy đủ thông tin"); 
      req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
      return;
    }

    // Thành công → chuyển sang /login và hiển thị thông báo success
    HttpSession s = req.getSession(true);
    s.setAttribute("register_success", "Đăng ký thành công, vui lòng đăng nhập");
    resp.sendRedirect(req.getContextPath() + "/login");
  }
}