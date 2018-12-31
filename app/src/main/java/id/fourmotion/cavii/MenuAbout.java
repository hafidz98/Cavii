package id.fourmotion.cavii;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MenuAbout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_about);
        setTitle("Tentang Kami");

        //Button
        final ImageButton homeButton = findViewById(R.id.menu_home);
        final ImageButton cariButton = findViewById(R.id.menu_cari);
        final ImageButton favButton = findViewById(R.id.menu_fav);
        final ImageButton aboutButton = findViewById(R.id.menu_about);

        homeButton.setSelected(false);
        cariButton.setSelected(false);
        favButton.setSelected(false);
        aboutButton.setSelected(true);

        cariButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuAbout.this, ListContent.class));
                overridePendingTransition(R.anim.no_transition, R.anim.no_transition);
            }
        });

        favButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuAbout.this, MenuFav.class));
                overridePendingTransition(R.anim.no_transition, R.anim.no_transition);
            }
        });

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuAbout.this, Mainmenu.class));
                overridePendingTransition(R.anim.no_transition, R.anim.no_transition);
            }
        });
    }
}
