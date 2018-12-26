package id.fourmotion.cavii;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;

import id.fourmotion.cavii.Adapter.ContentAdapter;
import id.fourmotion.cavii.Helper.MyDatabase;
import id.fourmotion.cavii.Model.Content;

public class ListContent extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ContentAdapter adapter;
    private ArrayList<Content> contentArrayList;
    private ArrayList<String> dataEkstra;
    private MyDatabase db;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_content);

        //Take data from extras
        dataEkstra = getIntent().getStringArrayListExtra("dataSearch()");

        //Put textview categories
        TextView filterJenis = findViewById(R.id.jenis_filter);
        TextView filterBahan = findViewById(R.id.bahan_filter);

        String forJenis, forBahan;

        if (dataEkstra.get(1) == null) {
            forJenis = "Jenis Baju: Semua Jenis";
        } else {
            forJenis = "Jenis Baju: " + dataEkstra.get(1);
        }

        if (dataEkstra.get(2) == null) {
            forBahan = "Bahan Baju: Semua Bahan";
        } else {
            forBahan = "Bahan Baju: " + dataEkstra.get(2);
        }

        filterJenis.setText(forJenis);
        filterBahan.setText(forBahan);

        db = new MyDatabase(this);

        recyclerView = findViewById(R.id.rv_content);
        if (dataEkstra.get(0) == null || dataEkstra.get(0).length() < 1) {
            try {
                defaultData();
                //adapter.setContext(ListContent.this);
                adapter = new ContentAdapter(db.getSearchKonveksi(dataEkstra.get(1), dataEkstra.get(2)));
            } catch (Exception e) {
                //Data tidak ditemukan
                Toast.makeText(ListContent.this, "Yah konveksi kamu tidak ditemukan", Toast.LENGTH_SHORT).show();
                defaultData();
            }
        } else {
            //pake search view
            //adapter = new ContentAdapter(db.getSearchView(dataEkstra.get(0)));
        }

        //show data from database
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ListContent.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        //Click item in recycler view
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                //WARNING!!! Ganti ke ID from content
                //String sendData = db.getSearchKonveksi(dataEkstra.get(1), dataEkstra.get(2)).get(position).get_id(); //

                //Toast.makeText(ListContent.this, db.getId(position), Toast.LENGTH_SHORT).show();

                //Send Intent
                Intent toDetailContent = new Intent(ListContent.this, DetailContent.class);
                toDetailContent.putExtra("trans_ID()", db.getId(position));

                startActivity(toDetailContent);
            }

            @Override
            public void onLongClick(View view, int position) {
            }
        }));
    }

    void defaultData() {
        contentArrayList = new ArrayList<>();
        contentArrayList.add(new Content("", "", "", "", "", "", null, null, null));
        adapter = new ContentAdapter(contentArrayList);
    }

    //interface for recycleView
    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }
}
