package com.example.senior_calendar_app;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class DataSender extends AsyncTask<String, Void, String> implements ProjectVariables {
    AlertDialog dialog;
    Context context;
    public DataSender(Context context){
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        dialog = new AlertDialog.Builder(context).create();
        dialog.setTitle("Write status");
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        // Here notification can be added
        dialog.setTitle(s);
        dialog.show();
    }

    @Override
    protected String doInBackground(String... strings) {
        String result = "";
        String URL = strings[0];
        String timestamp = strings[1];
        String user_id = strings[2];
        String temperature = strings[3];
        String weight = strings[4];
        String blood_plessure = strings[5];
        String heart_rate = strings[6];

        try {
            java.net.URL url = new URL(URL);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod("POST");
            http.setDoInput(true);
            http.setDoOutput(true);
            OutputStream ops = http.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(ops, "UTF-8")
            );
            String data = URLEncoder.encode(this.COL1_NAME, "UTF-8") + "="
                    + URLEncoder.encode(timestamp, "UTF-8") + "&"
                    + URLEncoder.encode(this.COL2_NAME, "UTF-8") + "="
                    + URLEncoder.encode(user_id, "UTF-8") + "&"
                    + URLEncoder.encode(this.COL3_NAME, "UTF-8") + "="
                    + URLEncoder.encode(temperature, "UTF-8") + "&"
                    + URLEncoder.encode(this.COL4_NAME, "UTF-8") + "="
                    + URLEncoder.encode(weight, "UTF-8") + "&"
                    + URLEncoder.encode(this.COL5_NAME, "UTF-8") + "="
                    + URLEncoder.encode(blood_plessure, "UTF-8") + "&"
                    + URLEncoder.encode(this.COL6_NAME, "UTF-8") + "="
                    + URLEncoder.encode(heart_rate, "UTF-8");
            Log.i("QUERY:", data);
            writer.write(data);
            writer.flush();
            writer.close();
            ops.close();
            InputStream ips = http.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(ips, "ISO-8859-1"));
            String line = "";
            while((line = reader.readLine()) != null){
                result += line;
            }
            reader.close();
            ips.close();
            http.disconnect();
        } catch (MalformedURLException e) {
            result = e.getMessage();
        } catch (IOException e) {
            result = e.getMessage();
        }
        return result;
    }
}
