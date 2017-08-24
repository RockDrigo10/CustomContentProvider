package com.example.admin.customcontentprovider.view.activity;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by Admin on 8/23/2017.
 */

public class CustomContenProvider extends ContentProvider {
    private static final String PROVIDER_NAME = "androidcontentproviderdemo.androidcontentprovider.cars";
    private static final Uri CONTENT_URI = Uri.parse("content://" + PROVIDER_NAME + "/cars");
    private static final int IMAGES = 1;
    private static final int IMAGE_ID = 2;
    private static final UriMatcher uriMatcher = getUriMatcher();
    private static UriMatcher getUriMatcher() {
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, "cars", IMAGES);
        uriMatcher.addURI(PROVIDER_NAME, "cars/#", IMAGE_ID);
        return uriMatcher;
    }

    private DataBaseHelper dataBaseHelper = null;

    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)) {
            case IMAGES:
                return "vnd.android.cursor.dir/vnd.com.androidcontentproviderdemo.androidcontentprovider.provider.cars";
            case IMAGE_ID:
                return "vnd.android.cursor.item/vnd.com.androidcontentproviderdemo.androidcontentprovider.provider.cars";

        }
        return "";
    }

    @Override
    public boolean onCreate() {
        Context context = getContext();
        dataBaseHelper = new DataBaseHelper(context);
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        String id = null;
        if(uriMatcher.match(uri) == IMAGE_ID) {
            //Query is for one single image. Get the ID from the URI.
            id = uri.getPathSegments().get(1);
        }
        return dataBaseHelper.getCarsSql(id, projection, selection, selectionArgs, sortOrder);
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        try {
            long id = dataBaseHelper.addNewImage(values);
            Uri returnUri = ContentUris.withAppendedId(CONTENT_URI, id);
            return returnUri;
        } catch(Exception e) {
            return null;
        }
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
