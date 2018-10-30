package fr.edouardkerhir.geolocmap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class ListActivity extends AppCompatActivity {

    private ListView mListTrip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        getCandy();
    }

    public void getCandy() {

        ListAdapter adapter = new ListAdapter(ListActivity.this, CandySingleton.getInstance().getCandyArrayList());
        mListTrip = findViewById(R.id.candy_list);
        mListTrip.setAdapter(adapter);
    }
}
