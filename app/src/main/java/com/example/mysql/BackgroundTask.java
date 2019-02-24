package com.example.mysql;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

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

public class BackgroundTask extends AsyncTask<String, Void, String> {

    private Context ctx;
    private AlertDialog alertDialog;

    BackgroundTask(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(ctx).create();
        alertDialog.setTitle("Login info...");
    }

    @Override
    protected String doInBackground(String... params) {
        String method = params[0];
        String insert_url = "http://192.168.43.11/shameed/insert.php";
        String get_url = "http://192.168.43.11/shameed/show.php";
        Log.i("ss", insert_url);
        if (method.equals("add_info")) {
            String id = params[1];
            String name = params[2];
            String dept = params[3];


            try {
                URL url = new URL(insert_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream os = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                String data = URLEncoder.encode("stu_id", "UTF-8") + "=" + URLEncoder.encode(id, "UTF-8") + "&" + URLEncoder.encode("stu_name", "UTF-8") +
                        "=" + URLEncoder.encode(name, "UTF-8") + "&" + URLEncoder.encode("stu_department", "UTF-8") + "=" + URLEncoder.encode(dept, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                os.close();
                InputStream is = httpURLConnection.getInputStream();
                is.close();
                Log.i("ss", "sucess");
                return "Register Successfully";
            } catch (MalformedURLException e) {
                Log.i("ss", e.toString());
                e.printStackTrace();
            } catch (IOException e) {
                Log.i("ss", e.toString());
                e.printStackTrace();
            }


        } else if (method.equals("get_info")) {
            String idd = params[1];
            String dept = params[2];
            try {
                URL url = new URL(get_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String data = URLEncoder.encode("user_name", "UTF-8") + "=" + URLEncoder.encode(idd, "UTF-8") + "&" + URLEncoder.encode("user_dept") + "=" +
                        URLEncoder.encode(dept, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String response = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    response += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return response;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {
        if (result.equals("Register Successfully")) {
            Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
            Log.i("ss", "done");
        } else {
            alertDialog.setMessage(result);
            alertDialog.show();

        }

    }
}
