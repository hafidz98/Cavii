package id.fourmotion.cavii;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.io.InputStream;
import java.text.NumberFormat;
import java.util.Locale;

import id.fourmotion.cavii.Helper.MyDatabase;

public class Mainmenu extends AppCompatActivity {

    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmenu);

        //Button
        ImageButton homeButton = findViewById(R.id.menu_home);
        homeButton.setSelected(true);

        // -----------Final data to send via intent-------
        ContentData contentData = new ContentData(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        super.onBackPressed();
        Toast.makeText(Mainmenu.this, "Keluar", Toast.LENGTH_LONG).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finishAffinity();
                finish();
            }
        }, 1000);
    }

    private class ContentData extends SQLiteAssetHelper {
        private TextView txtJudul, txtJenis, txtBahan, txtHarga, txtDesc;
        private ImageView imgPath;
        private static final String DATABASE_NAME = "db_cavii_v1.db";
        private static final int DATABASE_VERSION = 1;
        String phoneNumber, location;

        private ContentData(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            //txtJudul = findViewById(R.id.txt_judul_konveksi);
            txtJenis = findViewById(R.id.home_content_jenis);
            txtBahan = findViewById(R.id.txt_bahan);
            txtDesc = findViewById(R.id.txt_desc);
            txtHarga = findViewById(R.id.txt_harga);
            imgPath = findViewById(R.id.img_konveksi);
        }

        private void setData(String id) {
            SQLiteDatabase db = getWritableDatabase();
            String[] sqlSelect = {"_id", "cav_name", "cav_jen_name", "cav_ba_name", "cav_cost", "cav_pict", "cav_desc", "cav_phone_number", "cav_location_pin"};

            String qSelect = "SELECT cavii_trans._id, cavii_konveksi.cav_name, cavii_jenis.cav_jen_name, cavii_bahan.cav_ba_name, cavii_trans.cav_cost, cavii_trans.cav_pict, cavii_konveksi.cav_desc, cavii_konveksi.cav_phone_number, cavii_konveksi.cav_location_pin FROM cavii_trans " +
                    "INNER JOIN cavii_konveksi ON cavii_trans.cav_id = cavii_konveksi._id " +
                    "INNER JOIN cavii_jenis ON cavii_trans.cav_jen_id = cavii_jenis._id " +
                    "INNER JOIN cavii_bahan ON cavii_trans.cav_ba_id = cavii_bahan._id WHERE cavii_trans._id = ? ORDER BY cavii_trans.cav_cost ASC";

            //try {
            String[] searchParams = new String[]{id};

            Cursor c = db.rawQuery(qSelect, searchParams);
            c.moveToFirst();
//
            setTitle(c.getString(c.getColumnIndex(sqlSelect[1])));

            //txtJudul.setText(c.getString(c.getColumnIndex(sqlSelect[1])));
            txtJenis.setText(c.getString(c.getColumnIndex(sqlSelect[2])));
            txtBahan.setText(c.getString(c.getColumnIndex(sqlSelect[3])));
            //txtHarga.setText(c.getString(c.getColumnIndex(sqlSelect[4])));
            Locale localeID = new Locale("in", "ID");
            NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
            txtHarga.setText(formatRupiah.format((double) c.getDouble(c.getColumnIndex(sqlSelect[4]))));
            InputStream stream = this.getClass().getClassLoader().getResourceAsStream("assets/image/" + c.getString(c.getColumnIndex(sqlSelect[5])));
            Bitmap bitmap = BitmapFactory.decodeStream(stream);
            imgPath.setImageBitmap(bitmap);
            /*try {
                InputStream ims = getAssets().open(c.getString(c.getColumnIndex(sqlSelect[5])));

                Drawable d = Drawable.createFromStream(ims, null);
                imgPath.setImageDrawable(d);
            } catch (Exception e) {
                Toast.makeText(DetailContent.this, "" + e, Toast.LENGTH_SHORT).show();
            }*/
            txtDesc.setText(c.getString(c.getColumnIndex(sqlSelect[6])));
            phoneNumber = c.getString(c.getColumnIndex(sqlSelect[7]));
            location = c.getString(c.getColumnIndex(sqlSelect[8]));

            //Set button


            //contentList = new ArrayList<>();
            c.close();
            //} catch (Exception e) {
            //    Log.d("Errorgan", "" + e);
            //}
        }

        String getPhoneData() {
            return phoneNumber;
        }

        String getLocation() {
            return location;
        }
    }


    public void menuHome(View view) {
    }

    public void menuCari(View view) {
        startActivity(new Intent(Mainmenu.this, ListContent.class));
        overridePendingTransition(R.anim.no_transition, R.anim.no_transition);
    }

    public void menuFav(View view) {
        startActivity(new Intent(Mainmenu.this, MenuFav.class));
        overridePendingTransition(R.anim.no_transition, R.anim.no_transition);
    }

    public void menuAbout(View view) {
        startActivity(new Intent(Mainmenu.this, MenuAbout.class));
        overridePendingTransition(R.anim.no_transition, R.anim.no_transition);
    }
}