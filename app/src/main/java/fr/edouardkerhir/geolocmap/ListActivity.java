package fr.edouardkerhir.geolocmap;
import android.widget.ListView;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class ListActivity extends AppCompatActivity {
    private ListView mListTrip;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent goToMap = new Intent(ListActivity.this, MainActivity.class);
                    startActivity(goToMap);
                    return true;
                case R.id.navigation_dashboard:
                    Intent goToProfil = new Intent(ListActivity.this, ProfilActivity.class);
                    startActivity(goToProfil);
                    return true;
                case R.id.navigation_notifications:

                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        getCandy();
    }

    public void getCandy() {

        ListAdapter adapter = new ListAdapter(ListActivity.this, CandySingleton.getInstance().getCandyArrayList());
        mListTrip = findViewById(R.id.candy_list);
        mListTrip.setAdapter(adapter);

    }
}
