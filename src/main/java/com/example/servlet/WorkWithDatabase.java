package com.example.servlet;

import org.json.simple.JSONArray;

import java.sql.*;

public class WorkWithDatabase {

    private static final String URL = "jdbc:mysql://localhost:3306/construction_сompany_db";
//    private static final String USERNAME = "Xai_Cher_root";
//    private static final String PASSWORD = "pqBOFbG_Q4";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";


    private static Connection initWorkWithDatabase() {
        try {
            Driver driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);

            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            if (!connection.isClosed()) {
                System.out.println("Соединение с БД установлено!");
            }
            return connection;
        } catch (SQLException e) {
            System.out.println("Проблема с драйвером!");
            e.printStackTrace();
            return null;
        }
    }

    public static void create(String name, String email, String theme, String message, String date) {
        PreparedStatement ps = null;
        Connection connection = initWorkWithDatabase();
        try {
            String INSERT = "INSERT INTO requests (name, email, theme, message, date) VALUES (?, ?, ?, ?, ?)";
            ps = connection.prepareStatement(INSERT);
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, theme);
            ps.setString(4, message);
            ps.setString(5, date);

            ps.execute();
        } catch (SQLException e) {
            System.out.println("Проблема с вводом данных в БД");
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void update(int id, String name, String email, String theme, String message, String date) {
        PreparedStatement ps = null;
        Connection connection = initWorkWithDatabase();
        try {
            String UPDATE = "UPDATE requests SET name = ?, email = ?, theme = ?, message = ?, date = ? WHERE idRequests = ?";
            ps = connection.prepareStatement(UPDATE);

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, theme);
            ps.setString(4, message);
            ps.setString(5, date);
            ps.setInt(6, id);

            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Проблема с вводом данных в БД");
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void delete(int id) {
        Connection connection = initWorkWithDatabase();
        PreparedStatement ps = null;
        try {
            String DELETE = "DELETE FROM requests WHERE idRequests = ?";
            ps = connection.prepareStatement(DELETE);
            ps.setInt(1, id);

            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Проблема с удалением данных из БД");
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void read() {
        Connection connection = initWorkWithDatabase();
        try {
            String READ = "SELECT * FROM requests";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(READ);

            JSONArray feedbackList = new JSONArray();
            while (resultSet.next()) {
                DataRequests requests = new DataRequests(resultSet.getInt("idRequests"), resultSet.getString("name"), resultSet.getString("email"), resultSet.getString("theme"), resultSet.getString("message"), resultSet.getString("date"));
                feedbackList.add(requests.toJSON());
            }
            WorkWithJSON.setDataToJSON("requests.json", feedbackList);
        } catch (SQLException e) {
            System.out.println("Проблема с выводом данных из БД");
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
