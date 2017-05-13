package com.example.android.myapplication7;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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

public class MainActivity extends AppCompatActivity {
    String title;
    String authorList;
    ArrayList<Book> arrayList;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button mButton = (Button) findViewById(R.id.main_button);

        String string = "";
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = (EditText) findViewById(R.id.main_text);
                String uri = "https://www.googleapis.com/books/v1/volumes?q=" + editText.getText().toString();
                if (isOnline()) {
                    fetchBooks(uri);
                    setContentView(R.layout.list_view);
                } else {
                    toast();

                }

            }
        });
    }

    public void toast() {
        Toast.makeText(this, "No Internet Connection", Toast.LENGTH_LONG).show();
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    public void fetchBooks(String uri) {
        new FetchBookAsyncTask().execute(uri);
    }

    private class FetchBookAsyncTask extends AsyncTask<String, Void, List<Book>> {

        @Override
        protected List<Book> doInBackground(String... params) {

            HttpURLConnection httpURLConnection = null;
            BufferedReader bufferedReader = null;
            String JSONData = null;
            JSONObject jsonObject = null;

            try {
                URL url = new URL(params[0]);
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
                JSONArray jsonArray = jsonObject.getJSONArray("items");
                arrayList = new ArrayList<Book>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject object1 = jsonArray.getJSONObject(i);
                    JSONObject volumeInfo = object1.getJSONObject("volumeInfo");
                    title = volumeInfo.getString("title");
                    if (volumeInfo.has("authors")) {
                        JSONArray authors = volumeInfo.getJSONArray("authors");
                        String authorConcat = "";
                        for (int j = 0; j < authors.length(); j++) {
                            authorConcat += authors.get(j) + ", ";
                        }
                        authorList = authorConcat;
                    } else {
                        authorList = "No authors specified";
                    }
                    arrayList.add(new Book(title, authorList));
                }


            } catch (JSONException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;

        }

        public void emptyToast() {
            Toast.makeText(MainActivity.this, "Your Search is empty", Toast.LENGTH_LONG).show();

        }

        @Override
        protected void onPostExecute(List<Book> l) {
            super.onPostExecute(l);
            if (arrayList == null) {
                emptyToast();
                setContentView(R.layout.list_display);
                TextView textView = (TextView) findViewById(R.id.title_display);
                textView.setText("To avoid empty results modify your search criteria. Do not leave the search text field blank. Try searching for more general terms rather than specific.");
                return;
            }
            displayFunc();

        }


    }


    public void displayFunc() {
        setContentView(R.layout.list_view);
        BookAdapter adapter = new BookAdapter(this, arrayList);
        if (adapter != null) {
            listView = (ListView) findViewById(R.id.list);
            listView.setAdapter(adapter);
        } else {
            setContentView(R.layout.list_display);
            TextView textView = (TextView) findViewById(R.id.title_display);
            textView.setText("To avoid empty results modify your search criteria. Do not leave the search text field blank. Try searching for more general terms rather than specific.");
        }
    }

}
