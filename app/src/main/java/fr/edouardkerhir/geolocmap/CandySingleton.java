package fr.edouardkerhir.geolocmap;

import android.content.Context;

import java.util.ArrayList;

class CandySingleton {

    private static final CandySingleton ourInstance = new CandySingleton();

    private ArrayList<CandyModel> CandyArrayList = new ArrayList<>();

    private CandySingleton() {
    }

    static CandySingleton getInstance() {
        return ourInstance;
    }

    public ArrayList<CandyModel> getCandyArrayList() {
        CandyArrayList.clear();
        CandyArrayList.add(new CandyModel("Tagada", R.drawable.tagada, 5.39));
        CandyArrayList.add(new CandyModel("Dragibus", R.drawable.dragibus, 4.23));
        CandyArrayList.add(new CandyModel("Schtrumpf", R.drawable.schtroumpfs, 6.19));
        CandyArrayList.add(new CandyModel("Crocodile", R.drawable.crocodile, 6.19));
        CandyArrayList.add(new CandyModel("Chamalow", R.drawable.chamlow, 2.00));
        CandyArrayList.add(new CandyModel("Carambar", R.drawable.carambar, 6.50));
        CandyArrayList.add(new CandyModel("Reglisse", R.drawable.baton2, 8.57));
        CandyArrayList.add(new CandyModel("Koala", R.drawable.lutti, 6.19));
        CandyArrayList.add(new CandyModel("Scoubidou", R.drawable.lasso, 6.66));
        CandyArrayList.add(new CandyModel("Coca", R.drawable.cola, 5.71));

        return CandyArrayList;
    }
}
