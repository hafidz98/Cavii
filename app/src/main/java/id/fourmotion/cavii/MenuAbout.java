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
        ImageButton aboutButton = findViewById(R.id.menu_about);
        aboutButton.setSelected(true);
    }

    public void menuHome(View view) {
        startActivity(new Intent(MenuAbout.this, Mainmenu.class));
        overridePendingTransition(R.anim.no_transition, R.anim.no_transition);
    }

    public void menuCari(View view) {
        startActivity(new Intent(MenuAbout.this, ListContent.class));
        overridePendingTransition(R.anim.no_transition, R.anim.no_transition);
    }

    public void menuFav(View view) {
        startActivity(new Intent(MenuAbout.this, MenuFav.class));
        overridePendingTransition(R.anim.no_transition, R.anim.no_transition);
    }

    public void menuAbout(View view) {
    }
}
