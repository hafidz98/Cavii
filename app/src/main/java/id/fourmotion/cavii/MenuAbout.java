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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.anim_in_left, R.anim.anim_out_left);
        finish();
    }

    public void menuHome(View view) {
        startActivity(new Intent(MenuAbout.this, Mainmenu.class));
        overridePendingTransition(R.anim.no_transition, R.anim.no_transition);
        finish();
    }

    public void menuCari(View view) {
        startActivity(new Intent(MenuAbout.this, ListContent.class));
        overridePendingTransition(R.anim.no_transition, R.anim.no_transition);
        finish();
    }

    public void menuFav(View view) {
        startActivity(new Intent(MenuAbout.this, MenuFav.class));
        overridePendingTransition(R.anim.no_transition, R.anim.no_transition);
        finish();
    }

    public void menuAbout(View view) {
    }
}
