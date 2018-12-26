package id.fourmotion.cavii;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import id.fourmotion.cavii.Helper.MyDatabase;
import id.fourmotion.cavii.Model.Content;

public class DetailContent extends AppCompatActivity {

    String dataEkstra;
    private Content dataList;
    MyDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_content);

        try {

            dataEkstra = getIntent().getStringExtra("trans_ID()");

            dataList = db.getDetailKonveksi(dataEkstra);

            ContentData anu = new ContentData();

            anu.setContenData();

        } catch (Exception e) {
            Toast.makeText(this, "Terima: " + dataEkstra, Toast.LENGTH_SHORT).show();
            Log.d("Rusak",e.toString());
        }

        /*
        db.getDetailKonveksi("1");

        ContentData anu = new ContentData();
        anu.setContenData();
        */
    }

    /*
    public void onViewHolder(ContentData holder, int position){
        holder.txtJudul.setText(dataList.get(position).getJudul());

    }*/

    public class ContentData {
        private TextView txtJudul, txtJenis, txtBahan, txtHarga, txtDesc;
        private ImageView imgPath;
        private ImageButton btnPhone, btnWA, btnLoc;

        public ContentData() {
            txtJudul = findViewById(R.id.txt_judul_konveksi);
            txtJenis = findViewById(R.id.txt_jenis);
            txtBahan = findViewById(R.id.txt_bahan);
            txtDesc = findViewById(R.id.txt_desc);
            txtHarga = findViewById(R.id.txt_harga);
            imgPath = findViewById(R.id.img_konveksi);
            btnPhone = findViewById(R.id.tombol_kontak);
            btnWA = findViewById(R.id.tombol_wa);
            btnLoc = findViewById(R.id.tombol_maps);
        }

        public void setContenData() {
            txtJenis.setText(dataList.getJudul());
        }
    }


}