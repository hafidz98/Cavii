package id.fourmotion.cavii.Helper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;

import id.fourmotion.cavii.Model.Content;

import static java.lang.String.valueOf;

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

    private ArrayList<String> id = new ArrayList<>();

    public void setId (String Id){
        id.add(Id);
    }

    public  String getId (int position){
        return id.get(position);
    }



    public ArrayList<Content> getSearchKonveksi(String jenis, String bahan) {

        ArrayList<Content> contentArrayList;
        SQLiteDatabase db = getWritableDatabase();

        String[] sqlSelect = {"_id", "cav_name", "cav_jen_name", "cav_ba_name", "cav_cost", "cav_pict", "cav_phone_number", "cav_location_pin"};
        String qSelect;


        qSelect = "SELECT cavii_trans._id, cavii_konveksi.cav_name , cavii_jenis.cav_jen_name , cavii_bahan.cav_ba_name, cavii_trans.cav_cost, cavii_trans.cav_pict, cavii_konveksi.cav_desc, cavii_konveksi.cav_phone_number, cavii_konveksi.cav_location_pin FROM cavii_trans " +
                "INNER JOIN cavii_konveksi ON cavii_trans.cav_id = cavii_konveksi._id " +
                "INNER JOIN cavii_jenis ON cavii_trans.cav_jen_id = cavii_jenis._id " +
                "INNER JOIN cavii_bahan ON cavii_trans.cav_ba_id = cavii_bahan._id WHERE cavii_jenis.cav_jen_name LIKE ? OR cavii_bahan.cav_ba_name LIKE ? ORDER BY cavii_trans.cav_cost ASC";


        String[] searchParams = new String[]{valueOf(jenis) + "%", valueOf(bahan) + "%"};

        //selectionArgs = new String [] {String.valueOf(g),String.valueOf(s)};

        Cursor c = db.rawQuery(qSelect, searchParams);
        c.moveToFirst();


        //ArrayList<String> dbBahan = new ArrayList<>();
        contentArrayList = new ArrayList<>();

        try {
            //dbBahan.add("Pilih Bahan Baju: ");

            //Toast.makeText(MyDatabase.this, "jjj", Toast.LENGTH_SHORT).show();
            do {
                contentArrayList.add(new Content(
                        c.getString(c.getColumnIndex(sqlSelect[0])),
                        c.getString(c.getColumnIndex(sqlSelect[1])),
                        c.getString(c.getColumnIndex(sqlSelect[2])),
                        c.getString(c.getColumnIndex(sqlSelect[3])),
                        c.getString(c.getColumnIndex(sqlSelect[4])),
                        c.getString(c.getColumnIndex(sqlSelect[5])),
                        "", "", "")); //konten
                setId(c.getString(c.getColumnIndex(sqlSelect[0])));
            } while (c.moveToNext());
        } finally {
            c.close();
        }
        return contentArrayList;
    }


    public Content getDetailKonveksi(String id) {

        Content contentList;
        SQLiteDatabase db = getWritableDatabase();
        String[] sqlSelect = {"_id", "cav_name", "cav_jen_name", "cav_ba_name", "cav_cost", "cav_pict", "cav_desc", "cav_phone_number", "cav_location_pin"};

        String qSelect = "SELECT cavii_trans._id, cavii_konveksi.cav_name, cavii_jenis.cav_jen_name, cavii_bahan.cav_ba_name, cavii_trans.cav_cost, cavii_trans.cav_pict, cavii_konveksi.cav_desc, cavii_konveksi.cav_phone_number, cavii_konveksi.cav_location_pin FROM cavii_trans " +
                "INNER JOIN cavii_konveksi ON cavii_trans.cav_id = cavii_konveksi._id " +
                "INNER JOIN cavii_jenis ON cavii_trans.cav_jen_id = cavii_jenis._id " +
                "INNER JOIN cavii_bahan ON cavii_trans.cav_ba_id = cavii_bahan._id WHERE cavii_trans._id = ? ORDER BY cavii_trans.cav_cost ASC";

        contentList = new Content("","","","","","","","","");

        try {
            String[] searchParams = new String[]{id};



            Cursor c = db.rawQuery(qSelect, searchParams);
            c.moveToFirst();

            //contentList = new ArrayList<>();


            contentList = new Content(
                    c.getString(c.getColumnIndex(sqlSelect[0])),
                    c.getString(c.getColumnIndex(sqlSelect[1])),
                    c.getString(c.getColumnIndex(sqlSelect[2])),
                    c.getString(c.getColumnIndex(sqlSelect[3])),
                    c.getString(c.getColumnIndex(sqlSelect[4])),
                    c.getString(c.getColumnIndex(sqlSelect[5])),
                    c.getString(c.getColumnIndex(sqlSelect[6])),
                    c.getString(c.getColumnIndex(sqlSelect[7])),
                    c.getString(c.getColumnIndex(sqlSelect[8])));

            c.close();

            return contentList;

        }catch ( Exception e){
            Log.d("Cinta buta", "" + e);
        }
        return contentList;
    }
    //

}
