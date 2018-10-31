package fr.edouardkerhir.geolocmap;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.ArrayList;

public class CitrouilleListActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent goToMap = new Intent(CitrouilleListActivity.this, MainActivity.class);
                    startActivity(goToMap);
                    return true;
                case R.id.navigation_dashboard:

                    return true;
                case R.id.navigation_notifications:
                    Intent goToList = new Intent(CitrouilleListActivity.this, ListActivity.class);
                    startActivity(goToList);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citrouille_list);
        setTitle("Ma Panier");

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        SharedPreferences sharedPreferences = getSharedPreferences("mypref", MODE_PRIVATE);
        Gson gson = new Gson();
        String currentUser = sharedPreferences.getString("currentUser", null);
        UserModel userModel = gson.fromJson(currentUser, UserModel.class);

        ListView listView = findViewById(R.id.lv_citrouille);
        ArrayList<CandyModel> citrouille = userModel.getUsersCandies();

        CitrouilleAdapter adapter = new CitrouilleAdapter(this, citrouille);
        listView.setAdapter(adapter);
    }
}
