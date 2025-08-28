package com.example.app.controller;

import com.example.app.model.User;
import com.example.app.util.Constant;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/waiting")
public class WaitingController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute(Constant.SESSION_ACCOUNT) == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        User u = (User) session.getAttribute(Constant.SESSION_ACCOUNT);
        int role = u.getRoleid();
        if (role == 1) {
            resp.sendRedirect(req.getContextPath() + "/admin/home");
        } else if (role == 2) {
            resp.sendRedirect(req.getContextPath() + "/manager/home");
        } else {
            resp.sendRedirect(req.getContextPath() + "/home");
        }
    }
}