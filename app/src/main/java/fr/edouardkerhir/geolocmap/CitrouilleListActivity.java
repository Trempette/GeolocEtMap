package fr.edouardkerhir.geolocmap;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.ArrayList;

public class CitrouilleListActivity extends AppCompatActivity {

    SharedPreferences mPrefs = getPreferences(MODE_PRIVATE);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citrouille_list);
        setTitle("Ma Citrouille");

        Gson gson = new Gson();
        String json = mPrefs.getString("MyObject", "");
        UserModel user = gson.fromJson(json, UserModel.class);

        ListView listView = findViewById(R.id.lv_citrouille);
        ArrayList<CandyModel> citrouille = user.getUsersCandies();

        CitrouilleAdapter adapter = new CitrouilleAdapter(this, citrouille);
        listView.setAdapter(adapter);
    }
}
