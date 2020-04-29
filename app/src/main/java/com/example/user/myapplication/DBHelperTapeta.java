package com.example.user.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelperTapeta extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Running";
    private static final String TABLE_RUNNING = "Running";
    private static final String ID = "id";
    private static final String DATE = "date";
    private static final String DISTANCE = "distance";

    public DBHelperTapeta(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_RUNNING_TABLE = "CREATE TABLE " + TABLE_RUNNING + "("
                + ID + " TEXT," + DATE + " TEXT,"
                + DISTANCE + " TEXT" + ")";
        db.execSQL(CREATE_RUNNING_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RUNNING);

        onCreate(db);
    }

    public void addRekord(Rekord rekord) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ID, rekord.getId());
        values.put(DATE, rekord.getDate());
        values.put(DISTANCE, rekord.getDistance());

        db.insert(TABLE_RUNNING, null, values);
        db.close();
    }
    public ArrayList<Rekord> getAllRekords() {
        ArrayList<Rekord> rekordsList = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();
        String selectId = "SELECT id FROM " + TABLE_RUNNING;
        Cursor cursorId = db.rawQuery(selectId, null);
        String selectDate = "SELECT date FROM " + TABLE_RUNNING;
        Cursor cursorDate = db.rawQuery(selectDate, null);
        String selectDistance = "SELECT distance FROM " + TABLE_RUNNING;
        Cursor cursorDistance = db.rawQuery(selectDistance, null);


        if (cursorId.moveToFirst() && cursorDate.moveToFirst() && cursorDistance.moveToFirst()) {
            do {
                Rekord rekord = new Rekord();
                rekord.setId(cursorId.getString(0));
                rekord.setDate(cursorDate.getString(0));
                rekord.setDistance(cursorDistance.getString(0));

                rekordsList.add(rekord);
            } while (cursorId.moveToNext() && cursorDate.moveToNext()
                    && cursorDistance.moveToNext());
        }

        return rekordsList;
    }
    public void Clear(){
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete(TABLE_RUNNING, null, null);
    }
}
