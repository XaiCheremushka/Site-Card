package com.example.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "FeedBackRequestsServlet", urlPatterns = {"/requests"})
public class FeedBackRequestsServlet extends HttpServlet {
    private static final String filePath = "requests.json";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        JSONArray dataListJSON = WorkWithJSON.getDataFromJSON(filePath);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html><html lang=\"ru\"><head><meta charset=\"UTF-8\"><meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"><title>Заявки - Строительная компания</title><link href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css\" rel=\"stylesheet\"><link href=\"css/local.css\" rel=\"stylesheet\"><style>body {padding-top: 56px;}.result-section {margin-top: 20px;}</style></head><body><nav class=\"navbar navbar-expand-lg navbar-dark bg-dark fixed-top\"><div class=\"container\"><a class=\"navbar-brand\" href=\"index\">Строительная компания</a><button class=\"navbar-toggler\" type=\"button\" data-toggle=\"collapse\" data-target=\"#navbarResponsive\" aria-controls=\"navbarResponsive\" aria-expanded=\"false\" aria-label=\"Toggle navigation\"><span class=\"navbar-toggler-icon\"></span></button><div class=\"collapse navbar-collapse\" id=\"navbarResponsive\"><ul class=\"navbar-nav ml-auto\"><li class=\"nav-item\"><a class=\"nav-link\" href=\"index\">Услуги</a></li><li class=\"nav-item\"><a class=\"nav-link\" href=\"about\">О нас</a></li><li class=\"nav-item\"><a class=\"nav-link\" href=\"contact\">Контакты</a></li><li class=\"nav-item\"><a class=\"nav-link\" href=\"feedback\">Обратная связь</a></li></ul></div></div></nav><main><div class=\"container mt-5\"><h1 class=\"mt-5\">Заявки</h1><div class=\"result-section mt-5\"><h2>Все заявки</h2><table class=\"table table-bordered\"><thead><tr><th>Имя</th><th>Email</th><th>Тема</th><th>Сообщение</th><th>Дата</th></tr></thead><tbody id=\"requestsTableBody\">");
        for (Object obj : dataListJSON) {
            JSONObject data = (JSONObject) obj;
            DataRequests requests = DataRequests.fromJSON(data);
            assert requests != null;
            out.println("<tr><td>" + requests.getName() + "</td><td>" + requests.getEmail() + "</td><td>" + requests.getTheme() + "</td><td>" + requests.getMessage() + "</td><td>" + requests.getDate() + "</td>");
        }

        out.println("</tbody></table></div></div></main><footer class=\"py-5 bg-dark\"><div class=\"container\"><p class=\"m-0 text-center text-white\">© 2024 Строительная компания. Все права защищены.</p></div></footer></body></html>");

    }
}
