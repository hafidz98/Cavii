package id.fourmotion.cavii;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import id.fourmotion.cavii.Helper.MyDatabase;

public class Mainmenu extends AppCompatActivity {

    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmenu);

        //Button
        final ImageButton homeButton = findViewById(R.id.menu_home);
        final ImageButton cariButton = findViewById(R.id.menu_cari);
        final ImageButton favButton = findViewById(R.id.menu_fav);
        final ImageButton aboutButton = findViewById(R.id.menu_about);

        homeButton.setSelected(true);

        cariButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeButton.setSelected(false);
                startActivity(new Intent(Mainmenu.this, ListContent.class));
            }
        });

        favButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeButton.setSelected(false);
                startActivity(new Intent(Mainmenu.this, MenuFav.class));
            }
        });

        aboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeButton.setSelected(false);
                startActivity(new Intent(Mainmenu.this, MenuAbout.class));
            }
        });

        // -----------Final data to send via intent-------
        MyDatabase db;
        db = new MyDatabase(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}