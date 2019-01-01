package id.fourmotion.cavii;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;

public class MenuFav extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menufav);

        //Button
        ImageButton favButton = findViewById(R.id.menu_fav);
        favButton.setSelected(true);

    }

    public void menuHome(View view) {
        startActivity(new Intent(MenuFav.this, Mainmenu.class));
        overridePendingTransition(R.anim.no_transition, R.anim.no_transition);
    }

    public void menuCari(View view) {
        startActivity(new Intent(MenuFav.this, ListContent.class));
        overridePendingTransition(R.anim.no_transition, R.anim.no_transition);
    }

    public void menuFav(View view) {
    }

    public void menuAbout(View view) {
        startActivity(new Intent(MenuFav.this, MenuAbout.class));
        overridePendingTransition(R.anim.no_transition, R.anim.no_transition);
    }
}
