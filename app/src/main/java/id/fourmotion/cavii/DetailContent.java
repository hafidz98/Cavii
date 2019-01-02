package id.fourmotion.cavii;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.io.InputStream;
import java.text.NumberFormat;
import java.util.Locale;

public class DetailContent extends AppCompatActivity {

    String dataEkstra;
    //private Content dataList = new Content("","","aaa","","","","","","");
    //MyDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_content);

        final ContentData data = new ContentData(this);
        String idData = getIntent().getStringExtra("trans_ID()");
        data.setData(idData);

        ImageButton tombolKontak = findViewById(R.id.tombol_kontak);
        ImageButton tombolWA = findViewById(R.id.tombol_wa);
        ImageButton tombolPeta = findViewById(R.id.tombol_maps);

        try {
            tombolPeta.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Uri peta = Uri.parse("google.navigation:q=" + data.getLocation());
                    Intent map = new Intent(Intent.ACTION_VIEW, peta);
                    map.setPackage("com.google.android.apps.maps");
                    startActivity(map);
                }
            });
        } catch (Exception E) {
            Toast.makeText(this, "Install atau update Google Maps Anda", Toast.LENGTH_SHORT).show();
        }
        try {
            tombolWA.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Uri WA = Uri.parse("smsto: 0" + data.getPhoneData());
                    Intent whatsapp = new Intent(Intent.ACTION_SENDTO, WA);
                    whatsapp.setPackage("com.whatsapp");
                    startActivity(whatsapp);
                }
            });
        } catch (Exception e) {
            Toast.makeText(this, "Install atau update aplikasi WhatsApp Anda", Toast.LENGTH_SHORT).show();
        }
        try {
            tombolKontak.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Uri dial = Uri.parse("tel:" + data.getPhoneData());
                    Intent panggil = new Intent(Intent.ACTION_DIAL, dial);
                    startActivity(panggil);
                }
            });
        } catch (Exception e) {
            Toast.makeText(this, "Aktifkan perizinan kontak untuk mengakses fitur ini", Toast.LENGTH_LONG).show();
        }
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
            txtJenis = findViewById(R.id.txt_jenis_detail);
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

            setTitle(c.getString(c.getColumnIndex(sqlSelect[1])));


            txtJenis.setText(c.getString(c.getColumnIndex(sqlSelect[2])));
            txtBahan.setText(c.getString(c.getColumnIndex(sqlSelect[3])));
            Locale localeID = new Locale("in", "ID");
            NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
            txtHarga.setText(formatRupiah.format(c.getDouble(c.getColumnIndex(sqlSelect[4]))));
            InputStream stream = this.getClass().getClassLoader().getResourceAsStream("assets/image/" + c.getString(c.getColumnIndex(sqlSelect[5])));
            Bitmap bitmap = BitmapFactory.decodeStream(stream);
            imgPath.setImageBitmap(bitmap);
            txtDesc.setText(c.getString(c.getColumnIndex(sqlSelect[6])));
            phoneNumber = c.getString(c.getColumnIndex(sqlSelect[7]));
            location = c.getString(c.getColumnIndex(sqlSelect[8]));

            c.close();
        }

        String getPhoneData() {
            return phoneNumber;
        }

        String getLocation() {
            return location;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.anim_in_left, R.anim.anim_out_left);
        finish();
    }
}