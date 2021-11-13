package com.example.TvShowApp;

import android.annotation.SuppressLint;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;


public class TVMazeDatasource {
    RequestQueue requestQueue ;

    private String baseUrl = "https://www.api.tvmaze.com";
    private String url;

    private Consumer<List<Program>> onNewDataDo;
    private Consumer<Exception> onErrorDo;
    private List<Program> fetchedPrograms ;

    public TVMazeDatasource (RequestQueue requestQueue) {
        this.requestQueue = requestQueue;
    }
    public void fetchPrograms(Consumer<List<Program>> onNewDataDo, Consumer<Exception> onErrorDo, String kind) {
        this.onNewDataDo = onNewDataDo;
        this.onErrorDo = onErrorDo;

        getFullTVShowList("https://api.tvmaze.com/shows");
    }

    private void getFullTVShowList(String url) {
        this.url = url;
        this.fetchedPrograms = new ArrayList <Program> ();

        JsonArrayRequest arrReq = new JsonArrayRequest(Request.Method.GET, url, null, this.createRequestListener(), this.createErrorListener());
        requestQueue.add(arrReq);
    }


    private Response.ErrorListener createErrorListener() {
        return error -> {
            this.onErrorDo.accept(error);
            Log.e("Arrived data", "ERROR EXECUTING JSON CALL");
            Log.e("Volley", error.toString());
        };
    }

    private Response.Listener<JSONArray> createRequestListener() {
        return response -> {
            for (int i = 0; i < response.length(); i++) {
                try {
                    JSONObject jsonObj = response.getJSONObject(i);
                    this.processJsonObject(jsonObj);
                } catch (JSONException e) {
                    Log.e("Volley", "Invalid JSON Object.");
                }
            }
            this.onNewDataDo.accept(this.fetchedPrograms);
            this.fetchedPrograms = null;
        };

    }

    private void processJsonObject(JSONObject jsonObj) throws JSONException {
        String tvShowName = jsonObj.get("name").toString();
        String language = jsonObj.get("language").toString();
        String image = ((JSONObject) jsonObj.get("image")) .get ("medium").toString();
        Program program = new Program();
        program.setTitle(tvShowName);
        program.setDescription(language);
        program.setImageUrl (image);
        fetchedPrograms.add(program);
    }
}
