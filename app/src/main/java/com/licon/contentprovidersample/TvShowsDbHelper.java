package com.licon.contentprovidersample;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class TvShowsDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "contentprovidersample.db";
    private static final int DATABASE_VERSION = 1;

    // DB Table consts
    public static final String TVSHOWS_TABLE_NAME = "tvshows";
    public static final String TVSHOWS_COL_ID = "_id";
    public static final String TVSHOWS_COL_NAME = "name";
    public static final String TVSHOWS_COL_YEAR = "year";


    // Database creation sql statement
    public static final String DATABASE_CREATE = "create table "
            + TVSHOWS_TABLE_NAME + "(" +
            TVSHOWS_COL_ID + " integer   primary key autoincrement, " +
            TVSHOWS_COL_NAME + " text not null, " +
            TVSHOWS_COL_YEAR + " integer " +
            ");";

    public TvShowsDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(TvShowsDbHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TVSHOWS_TABLE_NAME);
        onCreate(db);
    }
}