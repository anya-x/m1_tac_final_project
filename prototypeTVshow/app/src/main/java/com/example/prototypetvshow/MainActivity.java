package com.example.prototypetvshow;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

public class MainActivity extends AppCompatActivity {

    TextView resultsList;
    RequestQueue requestQueue;

    String baseUrl = "https://www.api.tvmaze.com";
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.resultsList = (TextView) findViewById(R.id.resultsList);
        this.resultsList.setMovementMethod(new ScrollingMovementMethod());

        requestQueue = Volley.newRequestQueue(this);
        getFullTVShowList();
    }

    private void clearTVShowList() {
        this.resultsList.setText("emptyclear");
    }

    private void addTVShowList(String tvShowName, String language) {
        String strRow = tvShowName + " / " + language;
        String currentText = resultsList.getText().toString();
        this.resultsList.setText(currentText + "\n\n" + strRow);
    }

    private void setTVShowListText(String str) {
        this.resultsList.setText(str);
    }

    private void getFullTVShowList() {
        this.url = "https://api.tvmaze.com/shows";
        Response.Listener<JSONArray> listener = response -> {
            if (response.length() > 0) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObj = response.getJSONObject(i);
                        String tvShowName = jsonObj.get("name").toString();
                        String language = jsonObj.get("language").toString();
                        String image = jsonObj.get("language").toString();

                        addTVShowList(tvShowName, language);
                    } catch (JSONException e) {
                        Log.e("Volley", "Invalid JSON Object.");
                    }

                }
            } else {

                setTVShowListText("empty");
            }

        };

        Response.ErrorListener errorListener = error -> {
            setTVShowListText("Error while calling REST API");
            Log.e("Volley", error.toString());
        };

        JsonArrayRequest arrReq = new JsonArrayRequest(Request.Method.GET, url, null,listener, errorListener);

        requestQueue.add(arrReq);
    }
}