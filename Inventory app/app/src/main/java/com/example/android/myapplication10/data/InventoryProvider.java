package com.example.android.myapplication10.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class InventoryProvider extends ContentProvider {

    private InventoryDbHelper mInventoryDbHelper;

    private static final int ITEMS = 100;
    private static final int ITEM_ID = 101;

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);


    static {
        sUriMatcher.addURI(InventoryContract.CONTENT_AUTHORITY, InventoryContract.PATH, ITEMS);
        sUriMatcher.addURI(InventoryContract.CONTENT_AUTHORITY, InventoryContract.PATH + "/#", ITEM_ID);
    }

    @Override
    public boolean onCreate() {
        mInventoryDbHelper = new InventoryDbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        SQLiteDatabase sqLiteDatabase = mInventoryDbHelper.getReadableDatabase();
        Cursor localCursor;

        int match = sUriMatcher.match(uri);

        switch (match) {
            case ITEMS:
                localCursor = sqLiteDatabase.query(InventoryContract.InventoryEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            case ITEM_ID:
                selection = InventoryContract.InventoryEntry._ID + " =?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                localCursor = sqLiteDatabase.query(InventoryContract.InventoryEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);


                break;
            default:
                throw new IllegalArgumentException("Query does not match existing patterns.");
        }

        localCursor.setNotificationUri(getContext().getContentResolver(), uri);

        return localCursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        int match = sUriMatcher.match(uri);
        switch (match) {
            case ITEMS:
                return InventoryContract.InventoryEntry.CONTENT_LIST_TYPE;
            case ITEM_ID:
                return InventoryContract.InventoryEntry.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalArgumentException("Query does not match existing patterns.");
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        int match = sUriMatcher.match(uri);
        long id = -1;
        switch (match) {
            case ITEMS:
                SQLiteDatabase sqLiteDatabase = mInventoryDbHelper.getWritableDatabase();

                String name = values.getAsString(InventoryContract.InventoryEntry.COLUMN_PRODUCT_NAME);
                int quantity = values.getAsInteger(InventoryContract.InventoryEntry.COLUMN_QUANTITY);
                float price = values.getAsFloat(InventoryContract.InventoryEntry.COLUMN_PRICE);
                byte[] image = values.getAsByteArray(InventoryContract.InventoryEntry.COLUMN_IMAGE);

                if (name == null) {
                    throw new IllegalArgumentException("Name cannot be empty");
                }
                if (quantity < 0) {
                    throw new IllegalArgumentException("Quantity cannot be less than zero.");
                }
                if (price <= 0) {
                    throw new IllegalArgumentException("The price has to be greater than zero.");
                }
                if (image == null) {
                    throw new IllegalArgumentException("An image has to be present.");
                }

                id = sqLiteDatabase.insert(InventoryContract.InventoryEntry.TABLE_NAME, null, values);

                if (id == -1) {
                    return null;
                }
                break;

            default:
                throw new IllegalArgumentException("Wrong type of query " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return ContentUris.withAppendedId(uri, id);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {

        SQLiteDatabase sqLiteDatabase = mInventoryDbHelper.getWritableDatabase();
        int match = sUriMatcher.match(uri);
        int numOfRowsDeleted;
        switch (match) {
            case ITEMS:
                numOfRowsDeleted = sqLiteDatabase.delete(InventoryContract.InventoryEntry.TABLE_NAME, selection, selectionArgs);
                break;
            case ITEM_ID:
                selection = InventoryContract.InventoryEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                numOfRowsDeleted = sqLiteDatabase.delete(InventoryContract.InventoryEntry.TABLE_NAME, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Can't delete for this uri.");
        }
        if (numOfRowsDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return numOfRowsDeleted;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        int numOfRowsUpdated;
        SQLiteDatabase sqLiteDatabase = mInventoryDbHelper.getWritableDatabase();
        int match = sUriMatcher.match(uri);
        switch (match) {
            case ITEMS:
                numOfRowsUpdated = sqLiteDatabase.update(InventoryContract.InventoryEntry.TABLE_NAME,
                        values, selection, selectionArgs);

                break;
            case ITEM_ID:
                numOfRowsUpdated = sqLiteDatabase.update(InventoryContract.InventoryEntry.TABLE_NAME,
                        values, InventoryContract.InventoryEntry._ID + " =?", new String[]{String.valueOf(ContentUris.parseId(uri))});
                break;
            default:
                throw new IllegalArgumentException("Incorrect Uri." + uri);
        }
        if (numOfRowsUpdated > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }


        return numOfRowsUpdated;
    }
}

