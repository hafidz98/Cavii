package id.fourmotion.cavii;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ListContent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_content);

        //get data from previous activity
        Bundle receivedData = getIntent().getExtras();
        String data [] = receivedData.getStringArray("dataSearch()");

        //extract data from data array
        String dataSearch = data[0];
        String dataJenis = data[1];
        String dataBahan = data[2];
    }
}
