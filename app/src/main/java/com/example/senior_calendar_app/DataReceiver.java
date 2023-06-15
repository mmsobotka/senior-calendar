package com.example.senior_calendar_app;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

public class DataReceiver extends AsyncTask<String, Void, String> {
    AlertDialog dialog;
    Context context;

    public DataReceiver(Context context){
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        dialog = new AlertDialog.Builder(context).create();
        dialog.setTitle("Write status");
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String data) {

        int x_value, y_value;
        GraphView graph = (GraphView) ((Activity) context).findViewById(R.id.graph);

        if (data != null) {
            try {
                JSONObject jsonObject = new JSONObject(data);
                JSONArray arrJson = jsonObject.getJSONArray("data");
                DataPoint[] points = new DataPoint[arrJson.length()];
                for(int i = 0; i < arrJson.length(); i ++) {
                    x_value = i;
                    y_value = Integer.parseInt(arrJson.getString(i));
                    Log.i("Taged value", String.valueOf(y_value));
                    DataPoint data_point = new DataPoint(x_value, y_value);
                    points[i] = data_point;
                }
                LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(points);
                graph.addSeries(series);
                graph.getViewport().setMaxX(points.length);
                graph.getViewport().setXAxisBoundsManual(true);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        } else {
            DataPoint[] points = new DataPoint[5];
            for(int i = 0; i < 5; i ++) {
                x_value = i;
                y_value = 0;
                DataPoint data_point = new DataPoint(x_value, y_value);
                points[i] = data_point;
            }
            graph.removeAllSeries();
            LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(points);
            graph.addSeries(series);
        }
    }

    @Override
    protected String doInBackground(String... strings) {
        String URL = strings[0];
        String column_name = strings[1];
        String n_values = strings[2];
        String result = "";
        try {
            java.net.URL url = new URL(URL);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod("POST");
            http.setDoInput(true);
            http.setDoOutput(true);
            OutputStream ops = http.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(ops, "UTF-8"));
            String data = URLEncoder.encode("COL_NAME", "UTF-8") + "=" + URLEncoder.encode(column_name, "UTF-8") + "&"
                    + URLEncoder.encode("N_VALUES", "UTF-8") + "=" + URLEncoder.encode(n_values, "UTF-8");
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
        } catch (ProtocolException e) {
            result = e.getMessage();
        } catch (IOException e) {
            result = e.getMessage();
        }
        return result;
    }
}
