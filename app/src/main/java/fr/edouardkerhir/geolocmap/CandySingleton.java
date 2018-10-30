package fr.edouardkerhir.geolocmap;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.android.volley.toolbox.Volley.*;

class CandySingleton extends Context {
    private static final CandySingleton ourInstance = new CandySingleton();
    private ArrayList<Candy> CandyArrayList = new ArrayList<>();

    static CandySingleton getInstance() {
        return ourInstance;
    }

    private CandySingleton() {
    }

    public ArrayList<Candy> getCandyArrayList() {

        CandyArrayList.add(new Candy("Tagada", R.drawable.tagada));
        CandyArrayList.add(new Candy("Dragibus", R.drawable.dragibus));
        CandyArrayList.add(new Candy("Schtrumpf", R.drawable.schtroumpfs));
        CandyArrayList.add(new Candy("Crocodile", R.drawable.crocodile));
        CandyArrayList.add(new Candy("Chamalow", R.drawable.chama));
        CandyArrayList.add(new Candy("Pastille Vichy", R.drawable.pastille_du_bassin_de_vichy));
        CandyArrayList.add(new Candy("Reglisse", R.drawable.baton1));
        CandyArrayList.add(new Candy("Koala", R.drawable.chamalow));
        CandyArrayList.add(new Candy("Scoubidou", R.drawable.lasso_scoubidou));
        CandyArrayList.add(new Candy("Coca", R.drawable.haribo_happy_cola_bulk));

        return CandyArrayList;
    }
}
