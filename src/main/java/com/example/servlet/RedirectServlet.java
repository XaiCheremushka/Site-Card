package com.example.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "RedirectServlet", urlPatterns = {"/services", "/contact", "/about", "/index"})
public class RedirectServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        String targetPage = path.substring(1) + ".html";

        resp.setStatus(resp.SC_ACCEPTED);
        resp.setHeader("Location", targetPage);
        resp.sendRedirect(targetPage);
    }
}

