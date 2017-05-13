package com.example.android.myapplication8;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends ArrayAdapter<News> {


    ArrayList<News> refNews;

    public NewsAdapter(Context context, ArrayList<News> news) {
        super(context, 0, news);
        refNews = news;
    }


    @Override
    public News getItem(int position) {
        return refNews.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        News news = (News) getItem(position);


        convertView = LayoutInflater.from(getContext()).inflate(
                R.layout.list_display, parent, false);

        TextView mTextView1 = (TextView) convertView.findViewById(R.id.title_article);
        mTextView1.setText(news.getmTitle());
        TextView mTextView2 = (TextView) convertView.findViewById(R.id.title_section);
        mTextView2.setText(news.getmSection());
        TextView mTextView3 = (TextView) convertView.findViewById(R.id.title_web_url);
        mTextView3.setText(news.getmWebUrl());

        return convertView;
    }

    public void setNews(List<News> data) {
        refNews.addAll(data);
        notifyDataSetChanged();
    }

}
