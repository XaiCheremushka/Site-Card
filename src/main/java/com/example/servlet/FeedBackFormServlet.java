package com.example.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.time.LocalDate;

@WebServlet(name = "FeedBackFormServlet", urlPatterns = {"/feedback"})
public class FeedBackFormServlet extends HttpServlet {
    private static final String filePath = "requests.json";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Получение параметров формы
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String subject = request.getParameter("subject");
        String message = request.getParameter("message");
        LocalDate date = LocalDate.now();

        // Создание JSON объекта для отзыва
        JSONObject feedback = new JSONObject();
        feedback.put("name", name);
        feedback.put("email", email);
        feedback.put("subject", subject);
        feedback.put("message", message);
        feedback.put("date", date.toString());

        JSONArray feedbackList = new JSONArray();

        // Чтение существующих отзывов из файла
        File file = new File(filePath);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                JSONParser parser = new JSONParser();
                Object obj = parser.parse(reader);
                if (obj instanceof JSONArray) {
                    feedbackList = (JSONArray) obj;
                }
            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }
        }

        // Добавление нового отзыва в список
        feedbackList.add(feedback);
        System.out.println(feedbackList);

        // Запись обновленного списка отзывов обратно в файл
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            fileWriter.write(feedbackList.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        response.sendRedirect("feedback.html");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("feedback.html");
    }
}
