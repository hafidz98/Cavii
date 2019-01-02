package id.fourmotion.cavii;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.io.InputStream;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Random;

import id.fourmotion.cavii.Helper.MyDatabase;

public class Mainmenu extends AppCompatActivity {

    private String id;
    CarouselView carouselView;
    int[] gambarSlider = {R.drawable.banner, R.drawable.banner};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmenu);

        //Button
        ImageButton homeButton = findViewById(R.id.menu_home);
        homeButton.setSelected(true);

        // -----------Final data to send via intent-------
        ContentData contentData = new ContentData(this);
        contentData.setData(String.valueOf(new Random().nextInt(contentData.getSize()-1) + 1));

        carouselView = (CarouselView) findViewById(R.id.carouselViewer);
        carouselView.setPageCount(gambarSlider.length);

        carouselView.setImageListener(imageListener);
    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(gambarSlider[position]);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        }
    };

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Apakah Anda yakin ingin keluar?")
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Mainmenu.super.onBackPressed();
                    }
                })
                .setNegativeButton("Tidak", null)
                .show();
    }

    private class ContentData extends SQLiteAssetHelper {
        private TextView txtJudul, txtJenis, txtTempat, txtHarga;
        private ImageView imgPath;
        private static final String DATABASE_NAME = "db_cavii_v1.db";
        private static final int DATABASE_VERSION = 1;

        private ContentData(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            txtJudul = findViewById(R.id.home_content_judul);
            txtJenis = findViewById(R.id.home_content_jenis);
            txtHarga = findViewById(R.id.home_content_harga);
            imgPath = findViewById(R.id.home_content_gambar);
            txtTempat = findViewById(R.id.home_content_alamat);
        }

        private void setData(String id) {
            SQLiteDatabase db = getWritableDatabase();
            String[] sqlSelect = {"_id", "cav_name", "cav_jen_name", "cav_cost", "cav_pict", "cav_address"};

            String qSelect = "SELECT cavii_trans._id, cavii_konveksi.cav_name, cavii_jenis.cav_jen_name, cavii_trans.cav_cost, cavii_trans.cav_pict, cavii_konveksi.cav_address FROM cavii_trans " +
                    "INNER JOIN cavii_konveksi ON cavii_trans.cav_id = cavii_konveksi._id " +
                    "INNER JOIN cavii_jenis ON cavii_trans.cav_jen_id = cavii_jenis._id " +
                    "INNER JOIN cavii_bahan ON cavii_trans.cav_ba_id = cavii_bahan._id WHERE cavii_trans._id = ? ORDER BY cavii_trans.cav_cost ASC";

            String[] searchParams = new String[]{id};

            Cursor c = db.rawQuery(qSelect, searchParams);
            c.moveToFirst();

            txtJudul.setText(c.getString(c.getColumnIndex(sqlSelect[1])));
            txtJenis.setText(c.getString(c.getColumnIndex(sqlSelect[2])));
            Locale localeID = new Locale("in", "ID");
            NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
            txtHarga.setText(formatRupiah.format((double)c.getDouble(c.getColumnIndex(sqlSelect[3]))));
            InputStream stream = this.getClass().getClassLoader().getResourceAsStream("assets/image/" + c.getString(c.getColumnIndex(sqlSelect[4])));
            Bitmap bitmap = BitmapFactory.decodeStream(stream);
            imgPath.setImageBitmap(bitmap);
            txtTempat.setText(c.getString(c.getColumnIndex(sqlSelect[5])));
            c.close();
        }

        private int getSize(){
            SQLiteDatabase db = getWritableDatabase();
            String qSelect = "SELECT count(_id) FROM cavii_trans";

            Cursor c = db.rawQuery(qSelect,null);
            c.moveToFirst();
            int size =  c.getInt(c.getColumnIndex("count(_id)"));
            c.close();
            return size;
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