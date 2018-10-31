package fr.edouardkerhir.geolocmap;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

public class ProfilActivity extends AppCompatActivity {


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent goToMap = new Intent(ProfilActivity.this, MainActivity.class);
                    startActivity(goToMap);
                    return true;
                case R.id.navigation_dashboard:

                    return true;
                case R.id.navigation_notifications:
                    Intent goToList = new Intent(ProfilActivity.this, ListActivity.class);
                    startActivity(goToList);
                    return true;
            }
            return false;
        }
    };
    private UserModel user;
    private String    json;
    private TextView tv_nbBonbon;
    private TextView tv_pdBonbon;
    private TextView tvLevel;
    private EditText etpseudo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        setTitle("Mon Candy Profil");

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        final SharedPreferences mPrefs = getPreferences(MODE_PRIVATE);

        Gson gson = new Gson();
        json = mPrefs.getString("MyObject", "");
        user = gson.fromJson(json, UserModel.class);
        tv_nbBonbon = findViewById(R.id.tv_nbBonbon);
        tv_pdBonbon = findViewById(R.id.tv_pdBonbon);
        tvLevel = findViewById(R.id.tv_level);

     /*   if (user.getNom().length() < 0) {
            etpseudo.setText(user.getNom());
            tvLevel.setText(user.getLevel());
            int nbBonbon = user.getCandy();
            //double pdBonbon = user.getPoid();
            tv_nbBonbon.setText("Vous avez " + nbBonbon + " bonbons!");
            tv_pdBonbon.setText("Vous avez un poids de " + nbBonbon + "g de bonbons!");
        }

        etpseudo.setText(user.getNom());
        tvLevel.setText(user.getLevel());
        int nbBonbon = user.getCandy();
        double pdBonbon = user.getPoid();
        tv_nbBonbon.setText("Vous avez "+String.valueOf(nbBonbon)+" bonbons!");
        tv_pdBonbon.setText("Vous avez un poids de "+String.valueOf(pdBonbon)+"g de bonbons!");

        Button btUpdate = findViewById(R.id.bt_update);
        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String pseudo = etpseudo.getText().toString();
                user.setNom(pseudo);
                SharedPreferences.Editor prefsEditor = mPrefs.edit();
                prefsEditor.putString("user", json);
                prefsEditor.commit();

            }
        });

        Button btCitrouille = findViewById(R.id.bt_citrouille);
        btCitrouille.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToCitrouilleList = new Intent(ProfilActivity.this,
                        CitrouilleListActivity.class);
                startActivity(goToCitrouilleList);
            }
        });


    }

    public int getlevelUser(){

        UserModel user = new UserModel();
        int nbCandy = user.getCandy();
        int level = 0;

        if (nbCandy<10){
            level = 0;
        } else if (nbCandy>10 && nbCandy<20) {
            level = 1;
        } else if (nbCandy>20 && nbCandy<30) {
            level = 3;
        } else if (nbCandy>30 && nbCandy<40) {
            level = 4;
        } else if (nbCandy>40 && nbCandy<50) {
            level = 5;
        } else if (nbCandy>50 && nbCandy<60) {
            level = 6;
        } else if (nbCandy>60 && nbCandy<70) {
            level = 7;
        } else if (nbCandy>70 && nbCandy<80) {
            level = 8;
        } else if (nbCandy>80 && nbCandy<90) {
            level = 9;
        } else if (nbCandy>90 && nbCandy<100) {
            level = 10;
        } else if (nbCandy>100) {
            level = 11;
        }
        return  level;*/
    }
}
