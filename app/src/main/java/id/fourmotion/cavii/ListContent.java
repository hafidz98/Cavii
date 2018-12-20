package id.fourmotion.cavii;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;

import id.fourmotion.cavii.Adapter.ContentAdapter;
import id.fourmotion.cavii.Helper.MyDatabase;
import id.fourmotion.cavii.Model.Content;

public class ListContent extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ContentAdapter adapter;
    private ArrayList<Content> contentArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_content);

        //Take data from extras
        ArrayList<String> dataEkstra = getIntent().getStringArrayListExtra("dataSearch()");

        MyDatabase db = new MyDatabase(this);

        recyclerView = (RecyclerView) findViewById(R.id.rv_content);
        if (dataEkstra.get(0) == null || dataEkstra.get(0).length() <1){
            try {
                defaultData();
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
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ListContent.this);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);
    }

    void defaultData(){
        contentArrayList = new ArrayList<>();
        contentArrayList.add(new Content("","","",""));
        adapter = new ContentAdapter(contentArrayList);
    }
}
