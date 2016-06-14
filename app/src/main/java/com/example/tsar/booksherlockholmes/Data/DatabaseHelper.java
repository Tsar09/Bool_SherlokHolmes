package com.example.tsar.booksherlockholmes.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by tsar on 22.12.2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "SherlockHolmesDB.db";
    private static final String TEXT = "text";
    private static final String ID = "id";
    private static final String TABLE_NAME = "ScandalInBohemia";

    private static final int DATABASE_VERSION = 1;


    public DatabaseHelper(Context context){
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
    }
    
    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_WORDS_TABLE = "CREATE TABLE " + DictionaryProvider.TABLE_NAME + "("
                + DictionaryProvider._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + DictionaryProvider.COLUMN_WORD + " TEXT, "
                + DictionaryProvider.COLUMN_TRANSLATE + " TEXT"
                + ");";

        db.execSQL(SQL_CREATE_WORDS_TABLE);
        Log.e("MyLog", "DB CREATED");
        ContentValues cv = new ContentValues();
        for(int i = 1; i <3; i++ ){
            cv.put(DictionaryProvider.COLUMN_WORD, "word " + i);
            cv.put(DictionaryProvider.COLUMN_TRANSLATE, "tsarslate " + i);
            db.insert(DictionaryProvider.TABLE_NAME, null, cv);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public String getText(String id){
        Log.d("MyLog", "START TO GET FRAGMENT");
        SQLiteDatabase db = this.getReadableDatabase();
        String out = null;
        Log.d("MyLog", "TRY TO GET FRAGMENT");
        Cursor cursor = db.query(TABLE_NAME, new String[]{ID,TEXT}, ID + "=?",
                new String[]{id}, null, null, null, null);
        if (cursor != null && cursor.getCount() != 0) {
            cursor.moveToFirst();
            Log.d("MyLog", "TRY TO GET STRING");
            out = cursor.getString(1);
            //return cursor.getString(1);
        }
        cursor.close();
        return out;
    }
}
