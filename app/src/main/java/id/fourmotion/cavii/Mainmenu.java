package id.fourmotion.cavii;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;

public class Mainmenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmenu);

        final String input[] = new String[1];

        //Search View Override
        SearchView searchEngine = findViewById(R.id.search_engine);
        searchEngine.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //Mengambil data dari Search View
                input[0] = newText;
                return false;
            }
        });

        //Button Override
        Button tombolCari = findViewById(R.id.tombolcari);
        tombolCari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    //Place code here to send intent and data
                } catch (Exception e) {
                    Log.i("Error", "Button Error");
                }
            }
        });
    }
}