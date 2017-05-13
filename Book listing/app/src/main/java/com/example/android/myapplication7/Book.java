package com.example.android.myapplication7;

public class Book {

    private String mBookTitle;
    private String mAuthorNames;

    public Book(String title, String author) {
        mBookTitle = title;
        mAuthorNames = author;
    }

    public String getmBookTitle() {
        return mBookTitle;
    }

    public void setmBookTitle(String mBookTitle) {
        this.mBookTitle = mBookTitle;
    }

    public String getmAuthorNames() {
        return mAuthorNames;
    }

    public void setmAuthorNames(String mAuthorNames) {
        this.mAuthorNames = mAuthorNames;
    }

}
