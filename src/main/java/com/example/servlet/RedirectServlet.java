package com.example.servlet;

import java.io.IOException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RedirectServlet extends HttpServlet {
    @WebServlet(name = "Main", value = "/")
    public static class Main extends HttpServlet {
        public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
            response.sendRedirect("index.html");
        }
        public void destroy() {
        }
    }

    @WebServlet(name = "FeedBack", value = "/feedBack")
    public static class FeedBack extends HttpServlet {
        public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
            response.sendRedirect("feedBack.html");
        }
        public void destroy() {
        }
    }

    @WebServlet(name = "Contact", value = "/contact")
    public static class Contacts extends  HttpServlet {
        public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
            response.sendRedirect("contact.html");
        }
        public void destroy() {
        }
    }

    @WebServlet(name = "About", value = "/about")
    public static class AboutUs extends HttpServlet {
        public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
            response.sendRedirect("about.html");
        }
        public void destroy() {
        }
    }

    @WebServlet(name = "Services", value = "/services")
    public static class Services extends HttpServlet {
        public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
            response.sendRedirect("services.html");
        }
        public void destroy() {
        }
    }
}
