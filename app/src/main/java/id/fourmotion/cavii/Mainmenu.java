package id.fourmotion.cavii;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Mainmenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmenu);

        // -----------Final data to send via intent-------
        final String input[] = new String[1];
        final String selectedItemJenis[] = new String[1];
        final String selectedItemBahan[] = new String[1];

        // -----------Search View Override-----------
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

        // ---------Filter Jenis Override-------------
        final Spinner filterJenis = findViewById(R.id.jenis_filter);

        String[] jenisItem = new String[]{
                "Pilih jenis baju",
                "Kaos oblong",
                "Kaos berkerah",
                "Kemeja lengan pendek"
        };

        final ArrayAdapter<String> jenisArrayAdapter = new ArrayAdapter<>(
                this, R.layout.support_simple_spinner_dropdown_item, jenisItem);

        jenisArrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        filterJenis.setAdapter(jenisArrayAdapter);

        filterJenis.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Take data from selected item
                selectedItemBahan[0] = (String) parent.getItemAtPosition(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        // ---------Filter Jenis Override-------------
        final Spinner filterBahan = findViewById(R.id.bahan_filter);

        String[] jenisBahan = new String[]{
                "Pilih bahan baju",
                "Katun",
                "Sintetis",
                "Sutra"
        };

        final ArrayAdapter<String> bahanArrayAdapter = new ArrayAdapter<>(
                this, R.layout.support_simple_spinner_dropdown_item, jenisBahan);

        bahanArrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        filterBahan.setAdapter(bahanArrayAdapter);

        filterBahan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Take data from selected item
                selectedItemJenis[0] = (String) parent.getItemAtPosition(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        // -----------Button Override----------------
        Button tombolCari = findViewById(R.id.tombolcari);
        tombolCari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    // Place code here to send intent and data
                } catch (Exception e) {
                    Log.i("Error", "Button Error");
                }
            }
        });
    }
}