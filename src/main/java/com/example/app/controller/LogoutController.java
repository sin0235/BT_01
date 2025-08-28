package com.example.app.controller;

import com.example.app.util.Constant;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Remove session
        HttpSession session = req.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        
        // Remove remember me cookie
        Cookie cookie = new Cookie(Constant.COOKIE_REMEMBER, "");
        cookie.setPath("/");
        cookie.setMaxAge(0); // delete cookie
        cookie.setHttpOnly(true);
        resp.addCookie(cookie);
        
        resp.sendRedirect(req.getContextPath() + "/login");
    }
}