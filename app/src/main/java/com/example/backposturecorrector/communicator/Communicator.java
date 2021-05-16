package com.example.backposturecorrector.communicator;

import android.net.Uri;
import android.os.StrictMode;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class Communicator {

    public static String login(String sUrl, String email, String password) {
        HttpURLConnection connection = null;
        OutputStream out = null;

        try {

            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);

            URL url = new URL(sUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);
            connection.setRequestProperty("Accept-Charset", "UTF-8");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 ( compatible ) ");
            connection.setRequestProperty("Accept", "*/*");
            connection.setChunkedStreamingMode(0);
            StringBuilder stringBuilder = new StringBuilder();
            try {

                String input = "{ \"email\":" + "\"" + email + "\"" +
                        ",\"password\":" + "\"" + password + "\"" + "}";

                out = new BufferedOutputStream(connection.getOutputStream());
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));
                writer.write(input);
                writer.flush();
                writer.close();

                out.close();
                connection.connect();

                BufferedReader bufferedReader = new BufferedReader(
                        new InputStreamReader(connection.getInputStream()));

                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                bufferedReader.close();
                Log.d("AddUser-Response", stringBuilder.toString());
            } catch (Exception e) {
                System.out.println(e.getMessage());

            } finally {
                connection.disconnect();
                return stringBuilder.toString();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static String buildRequest(String url,
                                      Map<String, String> mapOfStrings) {

        Uri.Builder uriBuilder = new Uri.Builder();
        uriBuilder.encodedPath(url);
        if (mapOfStrings != null) {
            for (Map.Entry<String, String> entry : mapOfStrings.entrySet()) {
                Log.d("buildSanitizedRequest", "key: " + entry.getKey()
                        + " value: " + entry.getValue());
                uriBuilder.appendQueryParameter(entry.getKey(),
                        entry.getValue());
            }
        }
        String uriString;
        try {
            uriString = uriBuilder.build().toString(); // May throw an
            // UnsupportedOperationException
        } catch (Exception e) {
            Log.e("Exception", "Exception" + e);
        }

        return uriBuilder.build().toString();

    }

}
