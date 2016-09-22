package com.licon.contentprovidersample;

import android.net.Uri;

public class TvShowsContract {

    public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.licon.tvshow";
    public static final String CONTENT_TYPE_DIR = "vnd.android.cursor.dir/vnd.licon.tvshow";

    public static final String AUTHORITY = "com.licon.contentprovidersample.provider";
    // content://<authority>/<path to type>
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/tvshows");

    public static final String TV_SHOW_ID = "_id";
    public static final String TV_SHOW_NAME = "name";
    public static final String TV_SHOW_YEAR = "year";
}