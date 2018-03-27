package com.tubandev.catalogmovie.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.tubandev.catalogmovie.model.Result;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.tubandev.catalogmovie.database.DatabaseContract.MovieColumns.OVERVIEW;
import static com.tubandev.catalogmovie.database.DatabaseContract.MovieColumns.POPULARITY;
import static com.tubandev.catalogmovie.database.DatabaseContract.MovieColumns.RELEASE_DATE;
import static com.tubandev.catalogmovie.database.DatabaseContract.MovieColumns.TITLE;
import static com.tubandev.catalogmovie.database.DatabaseContract.TABLE_MOVIE;

/**
 * Created by sulistiyanto on 24/03/18.
 */

public class MovieHelper {


    private static String DATABASE_TABLE = TABLE_MOVIE;
    private Context context;
    private DatabaseHelper dataBaseHelper;

    private SQLiteDatabase database;

    public MovieHelper(Context context) {
        this.context = context;
    }


    public MovieHelper open() throws SQLException {
        dataBaseHelper = new DatabaseHelper(context);
        database = dataBaseHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        dataBaseHelper.close();
    }

    /*
    METHOD DIBAWAH INI UNTUK QUERY YANG LAMA ATAU UNTUK PROJECT SQLITE
     */

    /**
     * Gunakan method ini untuk ambil semua note yang ada
     * Otomatis di parsing ke dalam model Note
     * @return hasil query berbentuk array model note
     */
    public ArrayList<Result> query(){
        ArrayList<Result> arrayList = new ArrayList<Result>();
        Cursor cursor = database.query(DATABASE_TABLE
                ,null
                ,null
                ,null
                ,null
                ,null,_ID +" DESC"
                ,null);
        cursor.moveToFirst();
        Result movie;
        if (cursor.getCount()>0) {
            do {
                movie = new Result();
                movie.set_id(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                movie.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(TITLE)));
                movie.setReleaseDate(cursor.getString(cursor.getColumnIndexOrThrow(RELEASE_DATE)));
                movie.setOverview(cursor.getString(cursor.getColumnIndexOrThrow(OVERVIEW)));
                movie.setPopularity(Double.valueOf(cursor.getString(cursor.getColumnIndexOrThrow(POPULARITY))));

                arrayList.add(movie);
                cursor.moveToNext();

            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public long insert(Result movie){
        ContentValues initialValues =  new ContentValues();
        initialValues.put(TITLE, movie.getTitle());
        initialValues.put(RELEASE_DATE, movie.getReleaseDate());
        initialValues.put(OVERVIEW, movie.getOverview());
        initialValues.put(POPULARITY, movie.getPopularity() + "");
        return database.insert(DATABASE_TABLE, null, initialValues);
    }

    public int update(Result movie){
        ContentValues args = new ContentValues();
        args.put(TITLE, movie.getTitle());
        args.put(RELEASE_DATE, movie.getReleaseDate());
        args.put(OVERVIEW, movie.getOverview());
        args.put(POPULARITY, movie.getPopularity());
        return database.update(DATABASE_TABLE, args, _ID + "= '" + movie.get_id() + "'", null);
    }

    /**
     * Gunakan method ini untuk query delete
     * @param id id yang akan di delete
     * @return int jumlah row yang di delete
     */
    public int delete(int id){
        return database.delete(TABLE_MOVIE, _ID + " = '"+id+"'", null);
    }



    /*
    METHOD DI BAWAH INI ADALAH QUERY UNTUK CONTENT PROVIDER
    NILAI BALIK CURSOR
    */

    /**
     * Ambil data dari note berdasarakan parameter id
     * Gunakan method ini untuk ambil data di dalam provider
     * @param id id note yang dicari
     * @return cursor hasil query
     */
    public Cursor queryByIdProvider(String id){
        return database.query(DATABASE_TABLE,null
                ,_ID + " = ?"
                ,new String[]{id}
                ,null
                ,null
                ,null
                ,null);
    }

    /**
     * Ambil data dari semua note yang ada di dalam database
     * Gunakan method ini untuk ambil data di dalam provider
     * @return cursor hasil query
     */
    public Cursor queryProvider(){
        return database.query(DATABASE_TABLE
                ,null
                ,null
                ,null
                ,null
                ,null
                ,_ID + " DESC");
    }

    /**
     * Simpan data ke dalam database
     * Gunakan method ini untuk query insert di dalam provider
     * @param values nilai data yang akan di simpan
     * @return long id dari data yang baru saja di masukkan
     */
    public long insertProvider(ContentValues values){
        return database.insert(DATABASE_TABLE,null,values);
    }

    /**
     * Update data dalam database
     * @param id data dengan id berapa yang akan di update
     * @param values nilai data baru
     * @return int jumlah data yang ter-update
     */
    public int updateProvider(String id,ContentValues values){
        return database.update(DATABASE_TABLE,values,_ID +" = ?",new String[]{id} );
    }

    /**
     * Delete data dalam database
     * @param id data dengan id berapa yang akan di delete
     * @return int jumlah data yang ter-delete
     */
    public int deleteProvider(String id){
        return database.delete(DATABASE_TABLE,_ID + " = ?", new String[]{id});
    }
}
