package com.example.servlet;

import org.json.simple.JSONObject;

public class DataRequests {
    private String name;
    private String email;
    private String theme;
    private String message;
    private String date;

    public DataRequests(String name, String email, String theme, String message, String date) {
        this.name = name;
        this.email = email;
        this.theme = theme;
        this.message = message;
        this.date = date;
    }

    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public String getTheme() {
        return theme;
    }
    public String getMessage() {
        return message;
    }
    public String getDate() {
        return date;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setTheme(String theme) {
        this.theme = theme;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("email", email);
        json.put("theme", theme);
        json.put("message", message);
        json.put("date", date);

        return json;
    }

    public static DataRequests fromJSON(JSONObject json) {
        try {
            String name = (String) json.get("name");
            String email = (String) json.get("email");
            String theme = (String) json.get("theme");
            String message = (String) json.get("message");
            String date = (String) json.get("date");

            return new DataRequests(name, email, theme, message, date);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
