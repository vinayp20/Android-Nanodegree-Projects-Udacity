package com.example.android.myapplication6;

public class Location {

    private String mLocation;

    public String getmInformation() {
        return mInformation;
    }

    public void setmInformation(String mInformation) {
        this.mInformation = mInformation;
    }

    private String mInformation;

    private int mImageResourceId = NO_IMAGE_PROVIDED;

    private static final int NO_IMAGE_PROVIDED = -1;

    public Location(String location) {
        mLocation = location;
    }

    public Location(String location, String information) {
        mLocation = location;
        mInformation = information;
    }

    public Location(String location, int imageResourceID) {
        mLocation = location;
        mImageResourceId = imageResourceID;
    }

    public Location(String location, String information, int imageResourceID) {
        mLocation = location;
        mInformation = information;
        mImageResourceId = imageResourceID;
    }

    public String getmLocation() {
        return mLocation;
    }

    public void setmLocation(String mLocation) {
        this.mLocation = mLocation;
    }

    public int getImageResourceId() {
        return mImageResourceId;
    }

    public boolean hasImage() {
        if (mImageResourceId == -1) {
            return false;
        }
        return true;
    }


}
