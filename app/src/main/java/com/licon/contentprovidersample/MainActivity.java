package com.licon.contentprovidersample;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.System.currentTimeMillis;

public class MainActivity extends AppCompatActivity {

    private Button mBtnAddTvShow, mBtnShowAllList, mBtnClearList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnAddTvShow = (Button) findViewById(R.id.btnAddShow);
        mBtnAddTvShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tvshowsNames[] = getResources().getStringArray(R.array.tvshows_names);
                int tvshowsYears[] = getResources().getIntArray(R.array.tvshows_year);
                int randIdx = new Random(currentTimeMillis()).nextInt(tvshowsNames.length);
                TvShow tvShow = new TvShow(tvshowsNames[randIdx], tvshowsYears[randIdx]);

                /**
                 Add our Tv show to the local data base.
                 This normally should be done on a background thread
                 */
                getContentResolver().insert(TvShowsContract.CONTENT_URI, tvShow.getContentValues());
                Toast.makeText(MainActivity.this, "Added \"" + tvShow.toString() + "\"",
                        Toast.LENGTH_SHORT).show();
            }
        });

        mBtnShowAllList = (Button) findViewById(R.id.btnShowAllList);
        mBtnShowAllList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<TvShow> list = readFromContentProvider();
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setAdapter(new ArrayAdapter<TvShow>(MainActivity.this,
                        android.R.layout.simple_list_item_1, list),null);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).show();
            }
        });

        mBtnClearList = (Button) findViewById(R.id.btnClearLocalData);
        mBtnClearList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * Deleting all the TV Shows on the DB.
                 * This normally should be done on a background thread
                 */
                int numDeleted = getContentResolver().delete(TvShowsContract.CONTENT_URI, null, null);
                Toast.makeText(MainActivity.this, "Deleted " + numDeleted + " TV shows from the local list",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private List<TvShow> readFromContentProvider() {
        Cursor curTvShows = getContentResolver().query(TvShowsContract.CONTENT_URI, null, null, null, null);
        ArrayList<TvShow> shows = new ArrayList<TvShow>();
        if (curTvShows != null) {
            while (curTvShows.moveToNext())
                shows.add(TvShow.fromCursor(curTvShows));
            curTvShows.close();
        }
        return shows;
    }
}
