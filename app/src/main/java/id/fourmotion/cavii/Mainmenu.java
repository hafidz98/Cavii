package id.fourmotion.cavii;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import id.fourmotion.cavii.Helper.MyDatabase;

public class Mainmenu extends AppCompatActivity {

    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmenu);

        // -----------Final data to send via intent-------
        MyDatabase db;
        db = new MyDatabase(this);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setIcon(R.drawable.cavii_logo);
        actionBar.setDisplayUseLogoEnabled(true);

    }

    public void menuCari(View view) {
        startActivity(new Intent(this, ListContent.class));
        overridePendingTransition(R.anim.anim_in_right, R.anim.anim_out_right);
    }

    public void menuFav(View view) {
        startActivity(new Intent(this, MenuFav.class));
    }

    public void menuAbout(View view) {
        startActivity(new Intent(this, MenuFav.class));
    }
}