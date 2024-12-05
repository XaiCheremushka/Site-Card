package com.example.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;

import java.io.*;
import java.time.LocalDate;

@WebServlet(name = "FeedBackFormServlet", urlPatterns = {"/feedback"})
public class FeedBackFormServlet extends HttpServlet {
    private static final String filePath = "requests.json";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String theme = request.getParameter("theme");
        String message = request.getParameter("message");
        String date = LocalDate.now().toString();

        DataRequests requests = new DataRequests(name, email, theme, message, date);

        JSONArray feedbackList = WorkWithJSON.getDataFromJSON(filePath);
        feedbackList.add(requests.toJSON());
        WorkWithJSON.setDataToJSON(filePath, feedbackList);
        response.sendRedirect("feedback.html");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("feedback.html");
    }
}
