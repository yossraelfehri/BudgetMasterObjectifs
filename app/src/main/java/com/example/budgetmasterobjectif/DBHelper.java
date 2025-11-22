package com.example.budgetmasterobjectif;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "budget.db";
    private static final int DB_VERSION = 1;
    private static final String TABLE = "objectifs";

    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nom TEXT," +
                "montantCible REAL," +
                "montantAtteint REAL" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }

    public long addObjectif(String nom, double cible) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nom", nom);
        cv.put("montantCible", cible);
        cv.put("montantAtteint", 0.0);
        long id = db.insert(TABLE, null, cv);
        db.close();
        return id;
    }

    public ArrayList<Objectif> getAllObjectifs() {
        ArrayList<Objectif> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT id, nom, montantCible, montantAtteint FROM " + TABLE + " ORDER BY id DESC", null);
        if (c.moveToFirst()) {
            do {
                Objectif o = new Objectif();
                o.setId(c.getInt(0));
                o.setNom(c.getString(1));
                o.setMontantCible(c.getDouble(2));
                o.setMontantAtteint(c.getDouble(3));
                list.add(o);
            } while (c.moveToNext());
        }
        c.close();
        db.close();
        return list;
    }
}
