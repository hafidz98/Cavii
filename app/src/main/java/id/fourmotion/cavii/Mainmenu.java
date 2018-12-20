package id.fourmotion.cavii;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import id.fourmotion.cavii.Helper.MyDatabase;

public class Mainmenu extends AppCompatActivity {

    private String input, selectedItemJenis, selectedItemBahan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmenu);

        // -----------Final data to send via intent-------
        MyDatabase db;
        db = new MyDatabase(this);
        ArrayList<String> jens, bans;

        // ---------Filter Bahan Override-------------
        bans = db.getBahan();
        final Spinner filterBahan = findViewById(R.id.bahan_filter);
        final int selectBahan[] = new int[1];
        selectBahan[0] = 0;

        final ArrayAdapter<String> bahanArrayAdapter = new ArrayAdapter<String>(
                this, R.layout.support_simple_spinner_dropdown_item, bans) {
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup  parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == selectBahan[0]) {
                    // Set the item background color and text
                    tv.setBackgroundColor(Color.parseColor("#00A2E9"));
                    tv.setTextColor(Color.parseColor("#ffffff"));
                } else {
                    // Set the alternate item background color and text
                    tv.setBackgroundColor(Color.parseColor("#ffffff"));
                    tv.setTextColor(Color.parseColor("#000000"));
                }
                return view;
            }
        };
        bahanArrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        filterBahan.setAdapter(bahanArrayAdapter);

        filterBahan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Take data from selected item
                selectBahan[0] = parent.getSelectedItemPosition();
                if (selectBahan[0] == 0) {
                    selectedItemBahan = null;
                } else {
                    selectedItemBahan = (String) parent.getItemAtPosition(position);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        // ---------Filter Jenis Override-------------
        jens = db.getJenis();
        final int selectJenis[] = new int[1];
        selectJenis[0] = 0;
        final Spinner filterJenis = findViewById(R.id.jenis_filter);

        final ArrayAdapter<String> jenisArrayAdapter = new ArrayAdapter<String>(
                this, R.layout.support_simple_spinner_dropdown_item, jens) {

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == selectJenis[0]) {
                    // Set the item background color and text
                    tv.setBackgroundColor(Color.parseColor("#00A2E9"));
                    tv.setTextColor(Color.parseColor("#ffffff"));
                } else {
                    // Set the alternate item background color and text
                    tv.setBackgroundColor(Color.parseColor("#ffffff"));
                    tv.setTextColor(Color.parseColor("#000000"));
                }
                return view;
            }
        };


        jenisArrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        filterJenis.setAdapter(jenisArrayAdapter);

        filterJenis.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Take data from selected item
                selectJenis[0] = parent.getSelectedItemPosition();
                if (selectJenis[0] == 0) {
                    selectedItemJenis = null;

                    //set false to filter bahan
                    filterBahan.setSelection(0);
                    selectBahan[0] = 0;
                    filterBahan.setEnabled(false);
                    filterBahan.setClickable(false);

                } else {
                    selectedItemJenis = (String) parent.getItemAtPosition(position);

                    //set true to filter bahan
                    filterBahan.setEnabled(true);
                    filterBahan.setClickable(true);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        // -----------Search View Override-----------
        final SearchView searchEngine = findViewById(R.id.search_engine);
        searchEngine.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                input = s;
                return false;
            }
        });
    }

    public void cari(View view) {
        // Place code here to send intent and data
        //data di bawah ini sudah berupa string
        //input = get(0)
        //selectedItemJenis = get(1)
        //selectedItemBahan = get(2)
        if ((input == null || input.length() < 1) && (selectedItemJenis == null)){
            Toast.makeText(Mainmenu.this, "Isi filter atau pencarian", Toast.LENGTH_SHORT).show();
        } else {
            ArrayList<String> sendData = new ArrayList<>();
            sendData.add(input);
            sendData.add(selectedItemJenis);
            sendData.add(selectedItemBahan);

            Intent toListContent = new Intent(Mainmenu.this, ListContent.class);
            toListContent.putExtra("dataSearch()", sendData);

            startActivity(toListContent);
        }
    }
}