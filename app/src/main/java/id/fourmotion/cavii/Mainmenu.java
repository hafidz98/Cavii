package id.fourmotion.cavii;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;

public class Mainmenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmenu);

        // -----------Final data to send via intent-------
        final String input[] = new String[1];
        final String selectedItemJenis[] = new String[1];
        final String selectedItemBahan[] = new String[1];

        // ---------Filter Jenis Override-------------
        final int selectJenis[] = new int[1];
        selectJenis[0] = 0;

        final Spinner filterJenis = findViewById(R.id.jenis_filter);

        String[] jenisItem = new String[]{
                "Pilih jenis baju: ",
                "Kaos oblong",
                "Kaos berkerah",
                "Kemeja lengan pendek"
        };

        final ArrayAdapter<String> jenisArrayAdapter = new ArrayAdapter<String>(
                this, R.layout.support_simple_spinner_dropdown_item, jenisItem) {
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
                selectedItemJenis[0] = (String) parent.getItemAtPosition(position);
                selectJenis[0] = parent.getSelectedItemPosition();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        // ---------Filter Jenis Override-------------
        final Spinner filterBahan = findViewById(R.id.bahan_filter);
        final int selectBahan[] = new int[1];
        selectBahan[0] = 0;

        String[] jenisBahan = new String[]{
                "Pilih bahan baju: ",
                "Katun",
                "Sintetis",
                "Sutra"
        };

        final ArrayAdapter<String> bahanArrayAdapter = new ArrayAdapter<String>(
                this, R.layout.support_simple_spinner_dropdown_item, jenisBahan) {
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
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
                selectedItemBahan[0] = (String) parent.getItemAtPosition(position);
                selectBahan[0] = parent.getSelectedItemPosition();
                //tes

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // -----------Search View Override-----------
        final SearchView searchEngine = findViewById(R.id.search_engine);
        searchEngine.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchEngine.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        input[0] = (String) query;
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        return false;
                    }
                });
            }
        });


        // -----------Button Override----------------
        Button tombolCari = findViewById(R.id.tombolcari);
        tombolCari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    // Place code here to send intent and data
                    //data di bawah ini sudah berupa string
                    //input[0] = 0
                    //selectedItemJenis[0] = 1
                    //selectedItemBahan[0] = 2
                    String sendData[] = {input[0], selectedItemJenis[0], selectedItemBahan[0]};

                    Intent toListContent = new Intent(Mainmenu.this, ListContent.class);
                    toListContent.putExtra("dataSearch()", sendData);

                    startActivity(toListContent);
                } catch (Exception e) {
                    Log.d("Error", "Sending Error");
                }
            }
        });
    }
}