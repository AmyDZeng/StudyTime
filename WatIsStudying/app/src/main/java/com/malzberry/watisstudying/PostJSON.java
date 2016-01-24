package com.malzberry.watisstudying;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;


import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONObject;
import org.json.JSONArray;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

/*
{id: String,
    coursePrefix: String,
    courseNumber: Number,
    location: String,
    checkinAmount : Number}
 */
public class PostJSON extends AsyncTask<String, Void, ArrayList<String>> {
    public AsyncResponse delegate = null;
    public PostJSON(AsyncResponse ar) { delegate = ar;  }
    ArrayList<String> output = new ArrayList<String>();

    @Override
    protected ArrayList<String> doInBackground(String... strings)  {
        try {
            JSONObject json = new JSONObject();
            json.accumulate("prefix", strings[0]);
            json.accumulate("number", strings[1]);
            json.accumulate("room", strings[2]);
            json.accumulate("description", strings[3]);

            HashMap<String, String> params = new HashMap<String, String>();

            params.put("prefix", strings[0]);
            params.put("number", strings[1]);
            params.put("room", strings[2]);
            params.put("description", strings[3]);

            String url = "https://watstudy.herokuapp.com/api/add";

            makeHttpRequest(url, "POST", params);
        } catch(Exception e){
            output.add(0, "ERROR");
            output.add(1, e.toString());
        }
        return output;
    }




        String charset = "UTF-8";
        HttpURLConnection conn;
        DataOutputStream wr;
        StringBuilder result;
        URL urlObj;
        JSONObject jObj = null;
        StringBuilder sbParams;
        String paramsString;

        public JSONObject makeHttpRequest(String url, String method,
                                          HashMap<String, String> params) {

            sbParams = new StringBuilder();
            int i = 0;
            for (String key : params.keySet()) {
                try {
                    if (i != 0){
                        sbParams.append("&");
                    }
                    sbParams.append(key).append("=")
                            .append(URLEncoder.encode(params.get(key), charset));

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                i++;
            }

            if (method.equals("POST")) {
                // request method is POST
                try {
                    urlObj = new URL(url);

                    conn = (HttpURLConnection) urlObj.openConnection();

                    conn.setDoOutput(true);

                    conn.setRequestMethod("POST");

                    conn.setRequestProperty("Accept-Charset", charset);

                    conn.setReadTimeout(10000);
                    conn.setConnectTimeout(15000);

                    conn.connect();

                    paramsString = sbParams.toString();

                    wr = new DataOutputStream(conn.getOutputStream());
                    wr.writeBytes(paramsString);
                    wr.flush();
                    wr.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else if(method.equals("GET")){
                // request method is GET

                if (sbParams.length() != 0) {
                    url += "?" + sbParams.toString();
                }

                try {
                    urlObj = new URL(url);

                    conn = (HttpURLConnection) urlObj.openConnection();

                    conn.setDoOutput(false);

                    conn.setRequestMethod("GET");

                    conn.setRequestProperty("Accept-Charset", charset);

                    conn.setConnectTimeout(15000);

                    conn.connect();

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            try {
                //Receive the response from the server
                InputStream in = new BufferedInputStream(conn.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                result = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }

                Log.d("JSON Parser", "result: " + result.toString());

            } catch (IOException e) {
                e.printStackTrace();
            }

            conn.disconnect();

            // try parse the string to a JSON object
            try {
                jObj = new JSONObject(result.toString());
            } catch (JSONException e) {
                Log.e("JSON Parser", "Error parsing data " + e.toString());
            }

            // return JSON Object
            return jObj;
        }




    @Override
    protected void onPostExecute(ArrayList<String> s) {
        super.onPostExecute(s);
        delegate.processFinish(s);
    }
}