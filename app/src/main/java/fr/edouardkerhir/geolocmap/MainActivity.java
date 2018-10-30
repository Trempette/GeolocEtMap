package fr.edouardkerhir.geolocmap;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;

import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListPopupWindow;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    final static double TOULOUSE_LATITUDE = 43.6043;
    final static double TOULOUSE_LONGITUDE = 1.4437;
    final static double TOULOUSE_LATITUDE_BORDURES_BOT = 43.565428;
    final static double TOULOUSE_LONGITUDE_BORDURES_BOT = 1.411854;
    final static double TOULOUSE_LATITUDE_BORDURES_TOP = 43.642094;
    final static double TOULOUSE_LONGITUDE_BORDURES_TOP = 1.480995;
    final static int DISTANCE_POUR_CHOPPER_LES_BONBONS = 50;
    final static int ZOOM_LVL_BY_DEFAULT = 13;

    private static final Object UserModel = new UserModel();
    private PopupWindow popUp;
    private LocationManager mLocationManager = null;
    private FusedLocationProviderClient mFusedLocationClient;
    private GoogleMap superMap;
    private Location userLocation;
    private String url;
    private ArrayList<Places> placesAdresses;
    private RequestQueue requestQueue;
    private float mZoom;
    private ArrayList<Marker> mMarkers;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:

                    return true;
                case R.id.navigation_dashboard:
                    Intent goToProfil = new Intent(MainActivity.this, ProfilActivity.class);
                    startActivity(goToProfil);
                    return true;
                case R.id.navigation_notifications:
                    Intent goToList = new Intent(MainActivity.this, ListActivity.class);
                    startActivity(goToList);
                    return true;
            }
            return false;
        }
    };

    //Création de l'activity.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mZoom = 18.0f;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        requestQueue = Volley.newRequestQueue(this);
        placesAdresses = new ArrayList<>();
        mMarkers = new ArrayList<>();
        MapView mMap = (MapView) findViewById(R.id.map_view);
        mMap.onCreate(savedInstanceState);
        createMap(mMap);
    }


    // onRequestPermissionResult : scrute les résultats des demande de permission. Quand est elle lancée ?
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 100: {

                // cas de notre demande d'autorisation

                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    initLocation();

                } else {//autorisation refusée

                }
                return;
            }
        }
    }

    // checkPermission : nom epxlicite. Permet de vérifier les permission GPS. si les autorisations sont là, lance initLocation. Sinon demande les autorisations.
    public void checkPermission(){
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // l'autorisation n'est pas acceptée

            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                //TODO : créer quelque chose pour dire à l'utilisateur qu'il doit vraiment autoriser pour le fonctionnement du border

                // l'autorisation a été refusée précédemment, on peut prévenir l'utilisateur ici

            } else {

                // l'autorisation n'a jamais été réclamée, on la demande à l'utilisateur

                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        100);
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                        100);

            }
        } else {

           initLocation();

        }

    }

    //moveCamera : zoom la caméra sur l'utilisateur. Pratique.
    private void moveCamera(Location location) {
        // zoome la camera sur la dernière position connue
        mZoom = superMap.getCameraPosition().zoom;
        LatLng latLong = new LatLng(location.getLatitude(), location.getLongitude());

        superMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLong, mZoom));


    }


    //initLocation : lance la gélocalisation après check des permissions GPS, si toutes les permissions sont accordées
    @SuppressLint({"Missing Permission", "MissingPermission"})
    public void initLocation(){
            superMap.setMyLocationEnabled(true); // position de l'utilisateur sur la carte

            // récupération de la dernière position connue de l'utilisateur
            mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
            mFusedLocationClient.getLastLocation()
                    .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            // Got last known location. In some rare situations this can be null.
                            if (location != null) {
                                // Logic to handle location object
                                LatLng latLong = new LatLng(location.getLatitude(), location.getLongitude());
                                CameraPosition cameraPosition = new CameraPosition.Builder()
                                        .target(latLong) // Sets the center of the map to
                                        .zoom(mZoom)                   // Sets the zoom
                                        .bearing(0.0f) // Sets the orientation of the camera to east
                                        .tilt(70.0f)    // Sets the tilt of the camera to 30 degrees
                                        .build();    // Creates a CameraPosition from the builder
                                superMap.animateCamera(CameraUpdateFactory.newCameraPosition(
                                        cameraPosition));
                                url = "https://api-adresse.data.gouv.fr/search/?q=citycode=31555&lng="+location.getLongitude()+"&lat="+location.getLatitude()+"&type=housenumber&limit=500";
                                requeteAPI(url);
                                //moveCamera(location);
                                userLocation = location;
                            }
                        }
                    });

            // modification de la position si l'utilisateur se déplace
            mLocationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
            final LocationListener locationListener = new LocationListener() {
                public void onLocationChanged(Location location) {
                    userLocation = location;
                    moveCamera(location);
                }

                public void onStatusChanged(String provider, int status, Bundle extras) {
                    //méthode appelée au changement de status lors du déroulement de l'activité.
                }

                public void onProviderEnabled(String provider) {
                    //Méthode appelée lorsque l'activité est lancée et que le bonhomme désactive son gps durant le déroulement de l'activité. le con.
                }

                public void onProviderDisabled(String provider) {
                    //Méthode appelée lorsque l'activité est lancée et que le bonhomme active son gps durant le déroulement de l'activité. Habile.
                }
            };
            // initialisation de la vérification du déplacement par GPS et par réseau WIFI
            mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
            mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
        }

    public void createMap(MapView map){

        map.onResume();
        map.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {

                try {
                    // Customise the styling of the base map using a JSON object defined
                    // in a raw resource file.
                    boolean success = googleMap.setMapStyle(
                            MapStyleOptions.loadRawResourceStyle(
                                    MainActivity.this, R.raw.json_style_map));

                    if (!success) {
                        Log.e("TAG", "Style parsing failed.");
                    }
                } catch (Resources.NotFoundException e) {
                    Log.e("TAG", "Can't find style. Error: ", e);
                }

                superMap = googleMap;

                LatLngBounds toulouseBounds = new LatLngBounds(
                        new LatLng(TOULOUSE_LATITUDE_BORDURES_BOT, TOULOUSE_LONGITUDE_BORDURES_BOT), new LatLng(TOULOUSE_LATITUDE_BORDURES_TOP, TOULOUSE_LONGITUDE_BORDURES_TOP));
                googleMap.setLatLngBoundsForCameraTarget(toulouseBounds);
                // By default, map zoom on Toulouse
                LatLng toulouse = new LatLng(TOULOUSE_LATITUDE, TOULOUSE_LONGITUDE);
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(toulouse, ZOOM_LVL_BY_DEFAULT));

                //Configuration map
                UiSettings mMapConfig = googleMap.getUiSettings();
                mMapConfig.setZoomControlsEnabled(true);
                mMapConfig.setMyLocationButtonEnabled(true);
                mMapConfig.setCompassEnabled(true);
                mMapConfig.setTiltGesturesEnabled(true);

                checkPermission();
            }
        });

    }

    public void requeteAPI(String urlRequete){
        // Création de la requête vers l'API, ajout des écouteurs pour les réponses et erreurs possibles
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, urlRequete, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray adresses = response.getJSONArray("features");
                            placesAdresses.clear();
                            for (int i = 0; i < adresses.length(); i++) {
                                JSONObject adresseInfo = (JSONObject) adresses.get(i);
                                JSONObject properties = (JSONObject) adresseInfo.get("properties");
                                JSONObject geometry = (JSONObject) adresseInfo.get("geometry");
                                JSONArray coordinates = (JSONArray) geometry.get("coordinates");
                                String name = properties.getString("name");
                                String adress = properties.getString("label");
                                double longitude = coordinates.getDouble(0);
                                double latitude = coordinates.getDouble(1);
                                int nbCandy = (int) (Math.random()*4+1);
                                ArrayList<bonbonItemInfoWindow> candyThisPlace = new ArrayList<>();
                                for (int j=0; j<nbCandy; j++){
                                    int index = (int) (Math.random()*9+1);
                                    int nbForIndex = (int) (Math.random()*3+2);
                                    candyThisPlace.add(new bonbonItemInfoWindow(index, nbForIndex));
                                }

                                placesAdresses.add(new Places(name, adress, longitude, latitude, nbCandy, candyThisPlace));
                            }
                            createMarkers(placesAdresses);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Afficher l'erreur
                        Log.d("VOLLEY_ERROR", "onErrorResponse: " + error.getMessage());
                    }
                }
        );

        // On ajoute la requête à la file d'attente
        requestQueue.add(jsonObjectRequest);
    }

    public void createMarkers(ArrayList<Places> placesAdresses) {
        mMarkers.clear();
        for (final Places thisPlace : placesAdresses) {
            LatLng PlacePosition = new LatLng(thisPlace.getLatitude(), thisPlace.getLongitude());
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(PlacePosition);
            Marker marker = superMap.addMarker(markerOptions);
            BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.candyiconcolor);
            marker.setIcon(icon);
            marker.setTag(thisPlace);
            mMarkers.add(marker);
            boolean focus = false;
        }

        superMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                popupBuilder(marker);
                return false;
            }
        });
    }

    private void popupBuilder(final Marker marker) {

        Display display = getWindowManager().getDefaultDisplay();

        Point size = new Point();
        display.getSize(size);
        int width = (int) Math.round(size.x * 0.7);

        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        final View popUpView = inflater.inflate(R.layout.custom_info_window, null);

        //creation fenetre popup
        boolean focusable = true;
        popUp = new PopupWindow(popUpView, width, ListPopupWindow.WRAP_CONTENT, focusable);
        //show popup
        popUp.showAtLocation(popUpView, Gravity.CENTER, 0, 0);
        popUpView.setBackgroundResource(R.drawable.fond_popup);

        final Places place = (Places) marker.getTag();
        TextView placeName = popUpView.findViewById(R.id.place_name);
        placeName.setBackgroundResource(R.drawable.fond_title_popup);
        placeName.setText(place.getName());

        ListView candyList = popUpView.findViewById(R.id.candy_listview);
        ArrayList<bonbonItemInfoWindow> candyListItem = place.getCandyPlaces();

        CandyAdapter adapter = new CandyAdapter(this, candyListItem);
        candyList.setAdapter(adapter);

        final Button getCandy = popUpView.findViewById(R.id.button_get_candy);
        getCandy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (place.isVisited()) {
                    getCandy.setText("tu as déjà récupéré ces bonbons fdp!");
                } else {
                    if (getDistanceFromMarker(marker) < DISTANCE_POUR_CHOPPER_LES_BONBONS) {
                        Toast.makeText(MainActivity.this, "Tu es suffisament proche !", Toast.LENGTH_LONG).show();
                        BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.candyicongrey);
                        marker.setIcon(icon);
                        place.setVisited(true);
                    } else {
                        Toast.makeText(MainActivity.this, "Tu es trop loin !", Toast.LENGTH_LONG).show();
                    }
                    popUp.dismiss();
                }
            }
        });
    }

    public float getDistanceFromMarker(Marker marker){
        float distance;
        Places thisPlace = (Places) marker.getTag();
        Location thisPlaceLocation = new Location("");
        thisPlaceLocation.setLatitude(thisPlace.getLatitude());
        thisPlaceLocation.setLongitude(thisPlace.getLongitude());
        distance = thisPlaceLocation.distanceTo(userLocation);

        return distance;
    }
}
