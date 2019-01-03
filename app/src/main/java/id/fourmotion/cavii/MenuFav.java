package id.fourmotion.cavii;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

import id.fourmotion.cavii.Adapter.ContentAdapter;
import id.fourmotion.cavii.Helper.MyDatabase;
import id.fourmotion.cavii.Model.Content;

public class MenuFav extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MyDatabase db;
    private ArrayList<Content> contentArrayList;
    private ContentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menufav);

        getSupportActionBar().setTitle("Favorite");

        //Button//
        ImageButton favButton = findViewById(R.id.menu_fav);
        favButton.setSelected(true);

        tampilData();

    }

    void tampilData(){
        recyclerView = findViewById(R.id.rv_content_fav);
        try{
            db = new MyDatabase(MenuFav.this);
            adapter = new ContentAdapter(this,db.getSelectFavorite());
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);
        }catch (Exception e){
            Toast.makeText(this, "Kamu belum punya pakaian favorit", Toast.LENGTH_SHORT).show();
            defaultData();
            db.refreshId();
        }

    }

    void defaultData() {
        contentArrayList = new ArrayList<>();
        contentArrayList.add(new Content(null, "", "", "", "", "", "", "", "", "", ""));
        adapter = new ContentAdapter(this,contentArrayList);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.anim_in_left, R.anim.anim_out_left);
        finish();
    }

    public void menuHome(View view) {
        startActivity(new Intent(MenuFav.this, Mainmenu.class));
        overridePendingTransition(R.anim.no_transition, R.anim.no_transition);
        finish();
    }

    public void menuCari(View view) {
        startActivity(new Intent(MenuFav.this, ListContent.class));
        overridePendingTransition(R.anim.no_transition, R.anim.no_transition);
        finish();
    }

    public void menuFav(View view) {
    }

    public void menuAbout(View view) {
        startActivity(new Intent(MenuFav.this, MenuAbout.class));
        overridePendingTransition(R.anim.no_transition, R.anim.no_transition);
        finish();
    }
}
