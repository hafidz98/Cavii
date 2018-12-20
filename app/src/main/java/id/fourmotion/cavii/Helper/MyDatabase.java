package id.fourmotion.cavii.Helper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;

public class MyDatabase extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "db_cavii_v1.db";
    private static final int DATABASE_VERSION = 1;

    public MyDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    public ArrayList<String> getJenis() {
        SQLiteDatabase db = getWritableDatabase();

        String[] sqlSelect = {"_id", "cav_jen_name"};

        Cursor c = db.rawQuery("Select cav_jen_name from cavii_jenis", null);
        c.moveToFirst();

        ArrayList<String> dbJenis = new ArrayList<>();

        try {
            dbJenis.add("Pilih Baju: ");
            dbJenis.add(c.getString(c.getColumnIndex(sqlSelect[1]))); //konten
            while (c.moveToNext()) {
                dbJenis.add(c.getString(c.getColumnIndex(sqlSelect[1]))); //konten
            }
        } finally {
            c.close();
        }
        return dbJenis;
    }

    public ArrayList<String> getBahan() {
        SQLiteDatabase db = getWritableDatabase();

        String[] sqlSelect = {"_id", "cav_ba_name"};

        Cursor c = db.rawQuery("Select cav_ba_name from cavii_bahan", null);
        c.moveToFirst();

        ArrayList<String> dbBahan = new ArrayList<>();

        try {
            dbBahan.add("Pilih Bahan Baju: ");
            dbBahan.add(c.getString(c.getColumnIndex(sqlSelect[1]))); //konten
            while (c.moveToNext()) {
                dbBahan.add(c.getString(c.getColumnIndex(sqlSelect[1]))); //konten
            }
        } finally {
            c.close();
        }
        return dbBahan;
    }
}
