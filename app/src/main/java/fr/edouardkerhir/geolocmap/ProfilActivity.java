package fr.edouardkerhir.geolocmap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ProfilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        setTitle("Mon Candy Profil");
    }

    public int levelUser(){
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
        return  level;
    }
}
