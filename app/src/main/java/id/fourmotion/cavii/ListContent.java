package id.fourmotion.cavii;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.security.spec.ECField;
import java.util.ArrayList;
import java.util.List;

import id.fourmotion.cavii.Adapter.ContentAdapter;
import id.fourmotion.cavii.Helper.MyDatabase;
import id.fourmotion.cavii.Model.Content;

public class ListContent extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ContentAdapter adapter;
    private ArrayList<Content> contentArrayList;
    private MyDatabase db;
    private String inputSearch, selectedItemJenis, selectedItemBahan, id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_content);
        context = this;

        //Button
        ImageButton cariButton = findViewById(R.id.menu_cari);
        cariButton.setSelected(true);

        //ActionBar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Pencarian");

        // -----------Final data to send via intent-------
        ArrayList<String> jens, bans;

        // ---------Filter Bahan Override-------------
        db = new MyDatabase(this);
        bans = db.getBahan();
        final Spinner filterBahan = findViewById(R.id.bahan_filter);
        final int selectBahan[] = new int[1];
        selectBahan[0] = 0;

        final ArrayAdapter<String> bahanArrayAdapter = new ArrayAdapter<String>(
                this, R.layout.support_simple_spinner_dropdown_item, bans) {
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
                selectBahan[0] = parent.getSelectedItemPosition();
                if (selectBahan[0] == 0) {
                    selectedItemBahan = null;
                } else {
                    selectedItemBahan = (String) parent.getItemAtPosition(position);
                    tampilData();
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
                inputSearch = s;
                tampilData();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                return false;
            }
        });
    }

    void tampilData() {
        recyclerView = findViewById(R.id.rv_content);
        try {
            //adapter.setContext(ListContent.this);
            db = new MyDatabase(ListContent.this);
            adapter = new ContentAdapter(this, db.getSearchKonveksi(inputSearch, selectedItemJenis, selectedItemBahan));

        } catch (Exception e) {
            //Data tidak ditemukan
            Toast.makeText(ListContent.this, "Yah konveksi kamu tidak ditemukan", Toast.LENGTH_SHORT).show();
            defaultData();
            db.refreshId();
        }

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ListContent.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));

        /*
        Intent toDetailContent = new Intent(ListContent.this, DetailContent.class);
        toDetailContent.putExtra("trans_ID()", ContentAdapter.receiveID());
        startActivity(toDetailContent);
        overridePendingTransition(R.anim.anim_in_right, R.anim.anim_out_right);
        */
        //Click item in recycler view
        /*
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, final int position) {
                try {
                    //LinearLayout tombol_favorit = recyclerView.findViewWithTag("layout_favorit");
                    ImageView selectArea = recyclerView.findViewById(R.id.img_konveksi);
                    selectArea.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent toDetailContent = new Intent(ListContent.this, DetailContent.class);
                            toDetailContent.putExtra("trans_ID()", db.getId(position));
                            startActivity(toDetailContent);
                            overridePendingTransition(R.anim.anim_in_right, R.anim.anim_out_right);
                        }
                    });
                } catch (Exception e) {
                }
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));*/
    }

    void defaultData() {
        contentArrayList = new ArrayList<>();
        contentArrayList.add(new Content(null, "", "", "", "", "", "", "", "", "", ""));
        adapter = new ContentAdapter(this, contentArrayList);
    }

    //interface for recycleView


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.anim_in_left, R.anim.anim_out_left);
        finish();
    }

    public void menuHome(View view) {
        startActivity(new Intent(ListContent.this, Mainmenu.class));
        overridePendingTransition(R.anim.no_transition, R.anim.no_transition);
        finish();
    }

    public void menuCari(View view) {
    }

    public void menuFav(View view) {
        startActivity(new Intent(ListContent.this, MenuFav.class));
        overridePendingTransition(R.anim.no_transition, R.anim.no_transition);
        finish();
    }

    public void menuAbout(View view) {
        startActivity(new Intent(ListContent.this, MenuAbout.class));
        overridePendingTransition(R.anim.no_transition, R.anim.no_transition);
        finish();
    }

    static Context context;

    public static Context getContext() {
        return context;
    }

}
