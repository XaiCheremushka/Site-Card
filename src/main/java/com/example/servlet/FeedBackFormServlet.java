package com.example.servlet;

import java.io.*;
import java.time.LocalDate;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

@WebServlet(name = "feedback", value = "/feedback")
public class FeedBackFormServlet {
    private static final String filePath = "requests.json";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String subject = request.getParameter("subject");
        String message = request.getParameter("message");
//        String date = request.getParameter("date"); //fdf
        LocalDate date = LocalDate.now();

        JSONObject feedback = new JSONObject();
        feedback.put("name", name);
        feedback.put("email", email);
        feedback.put("subject", subject);
        feedback.put("message", message);
        feedback.put("date", date.toString());

        JSONArray feedbackList = new JSONArray();

        try {
            JSONParser parser = new JSONParser();
            File file = new File(filePath);
            String fullPath = file.getAbsolutePath();
            System.out.println(fullPath);
            if (file.exists()) {
                feedbackList = (JSONArray) parser.parse(new FileReader(filePath));
            }
            feedbackList.add(feedback);
            System.out.println("Feedback List: " + feedbackList);
            FileWriter fileWriter = new FileWriter(filePath);

            fileWriter.write(feedbackList.toJSONString());
            fileWriter.close();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
//        response.sendRedirect("feedback.html");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("feedback.html");
    }
}
