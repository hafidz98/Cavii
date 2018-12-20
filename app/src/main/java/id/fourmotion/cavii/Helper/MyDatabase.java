package id.fourmotion.cavii.Helper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class MyDatabase extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "db_cavii_v1.db";
    private static final int DATABASE_VERSION = 1;

    public MyDatabase(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public Cursor getJenis(){
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String [] sqlSelect = {"_id","cav_jen_name"};
        String sqlTables = "cavii_jenis";

        qb.setTables(sqlTables);
        Cursor c = qb.query(db, sqlSelect, null, null,null,null, null);
        c.moveToFirst();
        return c;
    }
}
