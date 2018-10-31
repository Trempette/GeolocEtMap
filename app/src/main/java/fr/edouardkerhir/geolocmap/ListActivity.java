package fr.edouardkerhir.geolocmap;
import android.media.MediaPlayer;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

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
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        viewPager = findViewById(R.id.vp);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
       /* monsterBuilder(1);
        addListenerOnSlider(viewPager);*/
        getCandy();

    }

    public void getCandy() {

        ListAdapter adapter = new ListAdapter(ListActivity.this, CandySingleton.getInstance().getCandyArrayList());
        mListTrip = findViewById(R.id.candy_list);
        mListTrip.setAdapter(adapter);

    }
   /* public void monsterBuilder(int indexEvolution) {

        //INITIALISATION VARIABLES
        TextView poid = findViewById(R.id.tv_poid_candy);

        //SETTING STATS
        if (poid != null) {
            String candyPoid = (String) poid.getText();
            if (indexEvolution == 1) {
                poid.setText(candyPoid);

            } else if (indexEvolution == 2) {
                poid.setText(String.valueOf(Integer.parseInt(candyPoid) * 2));

            } else {
                poid.setText(String.valueOf(Integer.parseInt(candyPoid) * 5));

            }

        }
    }

    public void addListenerOnSlider(ViewPager viewPager) {

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrollStateChanged(int state) {
            }

            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            public void onPageSelected(int position) {

                monsterBuilder(position + 1);

            }
        });

    }*/


}
