package com.ing.async.future;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class HelperUtility {

    public static List<String> getGods(String url) {
        List<String> allGods = new ArrayList<>();
        try{
            URL apiURL = new URL(url);
            HttpURLConnection con = (HttpURLConnection) apiURL.openConnection();;

            con.setRequestMethod("GET");
            con.setDoOutput(true);
            // give it 15 seconds to respond
            con.setReadTimeout(15 * 1000);
            con.connect();

            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));;
            String line;

            StringBuilder stringBuilder = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }

            String formattedOutput = stringBuilder.toString().replace("[", "").replace("]", "").replaceAll("\\s+", "").replace("\"", "").trim();
            allGods = Arrays.asList(formattedOutput.split(",")).stream()
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return allGods;

    }

    public static Integer getGodsInfo(String url) {
        StringBuilder stringBuilder = new StringBuilder();
        try{
            URL apiURL = new URL(url);
            HttpURLConnection con = (HttpURLConnection) apiURL.openConnection();;
            con.setRequestMethod("GET");
            con.setDoOutput(true);
            // give it 15 seconds to respond
            con.setReadTimeout(15 * 1000);
            con.connect();

            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));;
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringBuilder.toString().length();

    }
}
