package com.licon.contentprovidersample;

import android.content.ContentValues;
import android.database.Cursor;

import java.io.Serializable;

public class TvShow implements Serializable {

    public String name;
    public int year;

    public TvShow(String name, int year) {
        this.name = name;
        this.year = year;
    }

    public ContentValues getContentValues() {
        ContentValues values = new ContentValues();
        values.put(TvShowsDbHelper.TVSHOWS_COL_NAME, name);
        values.put(TvShowsDbHelper.TVSHOWS_COL_YEAR, year);
        return values;
    }

    public static TvShow fromCursor(Cursor curTvShows) {
        String name = curTvShows.getString(curTvShows.getColumnIndex(TvShowsDbHelper.TVSHOWS_COL_NAME));
        int year = curTvShows.getInt(curTvShows.getColumnIndex(TvShowsDbHelper.TVSHOWS_COL_YEAR));

        return new TvShow(name, year);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        TvShow tvShow = (TvShow) object;

        if (year != tvShow.year) return false;
        if (!name.equals(tvShow.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + year;
        return result;
    }

    @Override
    public String toString() {
        return name + " (" + year + ")";
    }
}