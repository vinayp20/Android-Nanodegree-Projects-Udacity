package com.example.android.myapplication8;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements LoaderCallbacks<List<News>>, SwipeRefreshLayout.OnRefreshListener {

    private Button mButton;
    private EditText mEdittext;
    ArrayList<News> arrayList;
    private NewsLoader mNewsLoader;
    private NewsAdapter newsAdapter;
    private String uri;
    private String query;
    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEdittext = (EditText) findViewById(R.id.edit_text);
        mButton = (Button) findViewById(R.id.button);


        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                query = mEdittext.getText().toString();
                uri = "https://content.guardianapis.com/search?q=" + query + "&api-key=7eba722e-8cfa-45c7-b196-7f09d11cfefd";

                if (isOnline()) {
                    getSupportLoaderManager().initLoader(0, null, MainActivity.this).forceLoad();

                } else {
                    toast();
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.refresh) {
            onRefresh();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void toast() {
        Toast.makeText(this, "No Internet Connection", Toast.LENGTH_LONG);
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    @Override
    public Loader<List<News>> onCreateLoader(int id, Bundle args) {

        return new NewsLoader(MainActivity.this, uri);
    }

    @Override
    public void onLoadFinished(Loader<List<News>> loader, final List<News> data) {

        if (data != null) {
            setContentView(R.layout.list_view);

            newsAdapter = new NewsAdapter(this, (ArrayList<News>) data);
            newsAdapter.setNews(new ArrayList<News>());


            if (newsAdapter != null) {
                listView = (ListView) findViewById(R.id.list);
                listView.setAdapter(newsAdapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        // setContentView(R.layout.activity_display);
                        News news = (News) listView.getItemAtPosition(position);
                        Uri uri = Uri.parse(news.getmWebUrl());
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(uri.toString()));
                        startActivity(intent);
                    }
                });
            } else {
                setContentView(R.layout.list_display);
                TextView textView = (TextView) findViewById(R.id.title_article);
                textView.setText("To avoid empty results modify your search criteria. Do not leave the search text field blank. Try searching for more general terms rather than specific.");

            }
        }
    }

    @Override
    public void onLoaderReset(Loader<List<News>> loader) {

        newsAdapter.setNews(new ArrayList<News>());

    }

    @Override
    public void onRefresh() {
        getSupportLoaderManager().restartLoader(0, null, MainActivity.this);
    }
}
