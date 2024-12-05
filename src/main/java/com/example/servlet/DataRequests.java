package com.example.servlet;

import org.json.simple.JSONObject;

public class DataRequests {

    private int id;
    private String name;
    private String email;
    private String theme;
    private String message;
    private String date;

    public DataRequests(int id, String name, String email, String theme, String message, String date) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.theme = theme;
        this.message = message;
        this.date = date;
    }

    public int getId() {
        return id;
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

    public void setId(int id) {
        this.id = id;
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
        json.put("id", id);
        json.put("name", name);
        json.put("email", email);
        json.put("theme", theme);
        json.put("message", message);
        json.put("date", date);

        return json;
    }

    public static DataRequests fromJSON(JSONObject json) {
        try {
            Long idLong = (Long) json.get("id");
            int id = idLong.intValue();

            String name = (String) json.get("name");
            String email = (String) json.get("email");
            String theme = (String) json.get("theme");
            String message = (String) json.get("message");
            String date = (String) json.get("date");

            return new DataRequests(id, name, email, theme, message, date);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String toText() {
        return "Request [name=" + name + ", email=" + email + ", theme=" + theme + "message=" + message + "date=" + date + "]";
    }

}
