package id.fourmotion.cavii.Helper;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class MyDatabase extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "db_cavii_v2.db";
    private static final int DATABASE_VERSION = 1;

    public MyDatabase(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
}
