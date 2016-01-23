package com.malzberry.watisstudying;


import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONObject;
import org.json.JSONArray;


public class ParseItemInfo extends AsyncTask<String, Void, String[]> {
    public AsyncResponse delegate = null;
    public ParseItemInfo(AsyncResponse ar) { delegate = ar;  }

    @Override
    protected String[] doInBackground(String... strings)  {
        StringBuffer buffer = new StringBuffer();
        JSONObject json;
        JSONArray jsonArr;
        String summonerId = "", summonerData = "", url = "";
        String output[] = {"", "", "", "", ""},  champUrl = "", id = "", playerUrl = "", rank = "";
        String playerIds[] = {"","","","","","","","","",""};

        try { //TODO: in the try, organize names by team numbers
            url =  "https://na.api.pvp.net/api/lol/na/v1.4/summoner/by-name/"
                    + strings[0].toLowerCase().replaceAll(" ", "%20")
                    + "?api_key=f491797f-4845-4a08-b280-a337e2fae904";

            json = JsonReader.readJsonFromUrl(url);
            summonerId = (json.getJSONObject(json.names().getString(0))).getString("id");

            // use ID to get raw data
            url = "https://na.api.pvp.net/observer-mode/rest/consumer/getSpectatorGameInfo/NA1/"
                    + summonerId + "?api_key=f491797f-4845-4a08-b280-a337e2fae904";

            summonerData = JsonReader.readStringJsonFromUrl(url);
        } catch(Exception e){
            // TODO: throw 2 different exceptions, player doesn't exist && player not in a game rn.
            output[0] = "error";
            //output[1] = e.toString();
            output[1] = "Player doesn't exist or isn't currently in game.";
            return output;
        }

        return output;
    }

    @Override
    protected void onPostExecute(String[] s) {
        super.onPostExecute(s);
        delegate.processFinish(s);
    }
}