package com.example.appdictionary.commandline;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

public class Translate {
    public static String translate(String langFrom, String langTo, String text) throws IOException {
        String _url = "https://script.google.com/macros/s/AKfycbzt3DKEZ95x28Vhi7kVoUKhTjymbnNtOy8q6Q67rkAsddozh5ab2Vql7_4V14ZbnBRG8g/exec" +
                "?q=" + URLEncoder.encode(text, "UTF-8") +
                "&target=" + langTo +
                "&source=" + langFrom;
        URL url = new URL(_url);
        StringBuilder response = new StringBuilder();
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("User", "Chrome/5.0");
        BufferedReader inputReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        while ((inputLine = inputReader.readLine()) != null) {
            response.append(inputLine);
        }
        inputReader.close();
        return response.toString();
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String text = sc.next();
        System.out.println("Translated text: " + translate("", "vi", text));
    }
}
