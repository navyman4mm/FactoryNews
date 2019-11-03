package com.example.newsfactory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.StrictMode;
import android.security.NetworkSecurityPolicy;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import Models.ArticlesItem;

public class MainActivity extends AppCompatActivity {

    private ArrayList<ArticlesItem> articlesList = new ArrayList<>();
    private RequestQueue mRequestQueue;
    private CountDownTimer timerRefresh;
    private RecyclerView recyclerView;
    private ProgressBar mProgressBar;
    private volatile Document doc = null;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        mRequestQueue = Volley.newRequestQueue(this);

        getNewsDataResponse();

    }



    public void init() {

        recyclerView = findViewById(R.id.recyclerView);

        if (recyclerView.getChildCount() > 0) {
            recyclerView.removeAllViews();
        }

        RecylerViewAdapter adapter = new RecylerViewAdapter(this, articlesList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    public void getNewsDataResponse() {


        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mProgressBar.setVisibility(View.VISIBLE);

        String url = "https://newsapi.org/v1/articles?source=bbc-news&sortBy=top&apiKey=6946d0c07a1c4555a4186bfcade76398";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {



                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            mProgressBar.setVisibility(View.INVISIBLE);
                            newRefreshTimer();
                            getNewsData(response);
                        } catch (JSONException | IOException e) {
                            e.printStackTrace();
                        }



                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //TODO: Make popup window!
                        mProgressBar.setVisibility(View.INVISIBLE);

                        startActivity(new Intent(MainActivity.this, Pop.class));

                    }
                });

        mRequestQueue.add(jsonObjectRequest);

    }


    public  void getNewsData(JSONObject response) throws JSONException, IOException {


        JSONArray articles = null;

        try {
            articles = response.getJSONArray("articles");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        articlesList.clear();


        for (int i = 0; i < articles.length(); i++) {

                articlesList.add(new ArticlesItem(
                        articles.getJSONObject(i).getString("publishedAt"),
                        articles.getJSONObject(i).getString("author"),
                        articles.getJSONObject(i).getString("urlToImage"),
                        articles.getJSONObject(i).getString("description"),
                        articles.getJSONObject(i).getString("title"),
                        getHtmlData(articles.getJSONObject(i).getString("url")
                        )
                ));



        }

        init();


    }

    private void newRefreshTimer() {
        timerRefresh = new CountDownTimer(30000L, 1000L) {

            @Override
            public void onTick(long interval) {}

            @Override
            public void onFinish() {
                getNewsDataResponse();
            }
        };
        timerRefresh.start();
    }

    private String getHtmlData(String url) throws IOException {


        Document doc = Jsoup.connect(url).get();


        Elements content = doc.getElementsByClass("story-body__inner");

        return content.toString();
    }


}
