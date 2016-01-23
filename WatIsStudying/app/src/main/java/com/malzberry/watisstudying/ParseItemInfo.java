package com.malzberry.watisstudying;


import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONObject;
import org.json.JSONArray;

import java.lang.reflect.Array;
import java.util.ArrayList;

/*
{id: String,
    coursePrefix: String,
    courseNumber: Number,
    location: String,
    checkinAmount : Number}
 */
public class ParseItemInfo extends AsyncTask<String, Void, ArrayList<String>> {
    public AsyncResponse delegate = null;
    public ParseItemInfo(AsyncResponse ar) { delegate = ar;  }
    ArrayList<String> output = new ArrayList<String>();

    @Override
    protected ArrayList<String> doInBackground(String... strings)  {
        StringBuffer buffer = new StringBuffer();
        JSONObject json;
        JSONArray jsonArr;
        String url = "https://watstudy.herokuapp.com/api/all";

        try {
            url =  strings[0];
            jsonArr = JsonReader.readJsonArrFromUrl(url);
            //json = JsonReader.readJsonFromUrl(url);

            for(int i = 0; i < jsonArr.length(); i++){
                // scrape info from json obj.
                json = jsonArr.getJSONObject(i);
                output.add(json.get("_id") + "," +
                        json.get("coursePrefix")+ "," +
                        json.get("courseNumber")+ "," +
                        json.get("location")+ "," +
                        json.get("checkinAmount"));
            }

        } catch(Exception e){
            output.add(0, "ERROR");
            output.add(1, e.toString());
        }
        return output;
    }

    @Override
    protected void onPostExecute(ArrayList<String> s) {
        super.onPostExecute(s);
        delegate.processFinish(s);
    }
}