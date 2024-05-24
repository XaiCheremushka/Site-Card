package com.example.servlet;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithJSON {
    public static void setDataToJSON(String filePath, JSONArray dataList) {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            fileWriter.write(dataList.toJSONString());
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static JSONArray getDataFromJSON(String filePath) {
        try {
            JSONParser parser = new JSONParser();
//            File file = new File(filePath);
//            String fullPath = file.getAbsolutePath();
//            System.out.println(fullPath);
            return (JSONArray) parser.parse(new FileReader(filePath));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return new JSONArray();
        }
    }
}
