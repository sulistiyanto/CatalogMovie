package com.tubandev.catalogmovie.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.tubandev.catalogmovie.database.DatabaseContract.TABLE_MOVIE;

/**
 * Created by sulistiyanto on 24/03/18.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "dbmovieapp";

    private static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_TABLE_MOVIE = String.format("CREATE TABLE %s"
                    + " (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL)",
            TABLE_MOVIE,
            DatabaseContract.MovieColumns._ID,
            DatabaseContract.MovieColumns.TITLE,
            DatabaseContract.MovieColumns.RELEASE_DATE,
            DatabaseContract.MovieColumns.POPULARITY,
            DatabaseContract.MovieColumns.OVERVIEW
    );

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_MOVIE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MOVIE);
        onCreate(db);
    }
}
