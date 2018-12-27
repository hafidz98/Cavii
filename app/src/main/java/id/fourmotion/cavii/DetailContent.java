package id.fourmotion.cavii;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;

import id.fourmotion.cavii.Helper.MyDatabase;
import id.fourmotion.cavii.Model.Content;

public class DetailContent extends AppCompatActivity {

    String dataEkstra;
    //private Content dataList = new Content("","","aaa","","","","","","");
    //MyDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_content);
        /*
        //db = new MyDatabase(this);

        try {

            //dataEkstra = getIntent().getStringExtra("trans_ID()");

            //dataList

            //dataList = db.getDetailKonveksi("3");

            //TextView text = findViewById(R.id.txt_jenis_detail);
            //text.setText(dataList.getJenis());

        } catch (Exception e) {
            //Toast.makeText(this, "Terima: " + dataList.getJenis(), Toast.LENGTH_SHORT).show();
            //Log.d("Rusak", e.toString());
        }


        db.getDetailKonveksi("1");

        ContentData anu = new ContentData();
        anu.setContenData();
        */

        ContentData data = new ContentData(this);
        data.setData("1");
    }

    /*
    public void onViewHolder(ContentData holder, int position){
        holder.txtJudul.setText(dataList.get(position).getJudul());

    }*/


    public class ContentData extends SQLiteAssetHelper {
        private TextView txtJudul, txtJenis, txtBahan, txtHarga, txtDesc;
        private ImageView imgPath;
        private ImageButton btnPhone, btnWA, btnLoc;
        private static final String DATABASE_NAME = "db_cavii_v1.db";
        private static final int DATABASE_VERSION = 1;

        public ContentData(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            txtJudul = findViewById(R.id.txt_judul_konveksi);
            txtJenis = findViewById(R.id.txt_jenis_detail);
            txtBahan = findViewById(R.id.txt_bahan);
            txtDesc = findViewById(R.id.txt_desc);
            txtHarga = findViewById(R.id.txt_harga);
            imgPath = findViewById(R.id.img_konveksi);
            btnPhone = findViewById(R.id.tombol_kontak);
            btnWA = findViewById(R.id.tombol_wa);
            btnLoc = findViewById(R.id.tombol_maps);
        }

        /*
        public void setContentData(Context konteks, String id) {
            konten = new MyDatabase(konteks).getDetailKonveksi(id);
            txtJudul.setText(konten.getJudul());
            //txtJenis.setText(konten.getJenis());
            //txtBahan.setText(konten.getBahan());
        }
        */

        public void setData(String id) {
            SQLiteDatabase db = getWritableDatabase();
            String[] sqlSelect = {"_id", "cav_name", "cav_jen_name", "cav_ba_name", "cav_cost", "cav_pict", "cav_desc", "cav_phone_number", "cav_location_pin"};

            String qSelect = "SELECT cavii_trans._id, cavii_konveksi.cav_name, cavii_jenis.cav_jen_name, cavii_bahan.cav_ba_name, cavii_trans.cav_cost, cavii_trans.cav_pict, cavii_konveksi.cav_desc, cavii_konveksi.cav_phone_number, cavii_konveksi.cav_location_pin FROM cavii_trans " +
                    "INNER JOIN cavii_konveksi ON cavii_trans.cav_id = cavii_konveksi._id " +
                    "INNER JOIN cavii_jenis ON cavii_trans.cav_jen_id = cavii_jenis._id " +
                    "INNER JOIN cavii_bahan ON cavii_trans.cav_ba_id = cavii_bahan._id WHERE cavii_trans._id = ? ORDER BY cavii_trans.cav_cost ASC";

            try {
                String[] searchParams = new String[]{id};


                Cursor c = db.rawQuery(qSelect, searchParams);
                c.moveToFirst();

                txtJenis.setText(c.getString(c.getColumnIndex(sqlSelect[2])));

                //contentList = new ArrayList<>();
                c.close();
            } catch (Exception e) {
                Log.d("Error gan", "" + e);
            }
        }
    }
}