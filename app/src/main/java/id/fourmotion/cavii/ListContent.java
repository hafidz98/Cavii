package id.fourmotion.cavii;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import id.fourmotion.cavii.Adapter.ContentAdapter;
import id.fourmotion.cavii.Model.Content;

public class ListContent extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ContentAdapter adapter;
    private ArrayList<Content> contentArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_content);

        addData();

        recyclerView = (RecyclerView) findViewById(R.id.rv_content);

        adapter = new ContentAdapter(contentArrayList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ListContent.this);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);
    }

    void addData(){
        contentArrayList = new ArrayList<>();
        contentArrayList.add(new Content("Kahoona Konveksi", "Kemeja","Katun","20000"));
        contentArrayList.add(new Content("Alesha Shirt", "Kemeja Panjang","Katun","25000"));
        contentArrayList.add(new Content("Konveksi Diana", "Kaos","Katun","30000"));
    }
}
