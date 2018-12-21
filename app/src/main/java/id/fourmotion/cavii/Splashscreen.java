package id.fourmotion.cavii;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

import com.squareup.picasso.Picasso;

public class Splashscreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        //Picasso.with(image.getContext()).load(storagePath).fit().centerCrop().into(image);



        // Handler delay activity splashscreen
        byte second = 3;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(Splashscreen.this, Mainmenu.class));
                finish();
            }
        }, 1000 * second);

        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
    }
}
