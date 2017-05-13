package com.example.android.myapplication8;

//import android.content.AsyncTaskLoader;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class NewsLoader extends AsyncTaskLoader<List<News>> {

    private String mUrl;
    private ArrayList<News> arrayList;
    private String title;
    private String section;
    private String webUrl;

    NewsLoader(Context context, String uri) {
        super(context);
        mUrl = uri;

    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    public ArrayList<News> loadInBackground() {


        HttpURLConnection httpURLConnection = null;
        BufferedReader bufferedReader = null;
        String JSONData = null;
        JSONObject jsonObject = null;

        try {
            URL url = new URL(mUrl);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.connect();

            InputStream inputStream = httpURLConnection.getInputStream();

            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            StringBuffer buffer = new StringBuffer();

            String line = "";

            while ((line = bufferedReader.readLine()) != null) {
                buffer.append(line);

            }
            bufferedReader.close();

            JSONData = buffer.toString();

            jsonObject = new JSONObject(JSONData);
            JSONObject jsonObject1 = jsonObject.getJSONObject("response");
            arrayList = new ArrayList<News>();

            for (int i = 0; i < jsonObject1.length(); i++) {
                JSONArray jsonArray = jsonObject1.getJSONArray("results");
                JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                title = jsonObject2.getString("webTitle");
                section = jsonObject2.getString("sectionName");
                webUrl = jsonObject2.getString("webUrl");
                News news = new News();
                news.setmTitle(title);
                news.setmSection(section);
                news.setmWebUrl(webUrl);

                arrayList.add(news);
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return arrayList;
    }
}
