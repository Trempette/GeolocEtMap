package fr.edouardkerhir.geolocmap;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.tasks.OnSuccessListener;

public class MainActivity extends AppCompatActivity {
    final static double TOULOUSE_LATITUDE = 43.6043;
    final static double TOULOUSE_LONGITUDE = 1.4437;
    final static double TOULOUSE_LATITUDE_BORDURES_BOT = 43.565428;
    final static double TOULOUSE_LONGITUDE_BORDURES_BOT = 1.411854;
    final static double TOULOUSE_LATITUDE_BORDURES_TOP = 43.642094;
    final static double TOULOUSE_LONGITUDE_BORDURES_TOP = 1.480995;
    final static int ZOOM_LVL_BY_DEFAULT = 13;

    private LocationManager mLocationManager = null;
    private FusedLocationProviderClient mFusedLocationClient;
    private GoogleMap superMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MapView mMap = (MapView) findViewById(R.id.map_view);
        mMap.onCreate(savedInstanceState);
        createMap(mMap);

    }

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

    private void moveCamera(Location location) {
        // zoome la camera sur la dernière position connue
        LatLng latLong = new LatLng(location.getLatitude(), location.getLongitude());
        superMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLong, 17.0f));
    }

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
                                moveCamera(location);
                            }
                        }
                    });

            // modification de la position si l'utilisateur se déplace
            mLocationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
            final LocationListener locationListener = new LocationListener() {
                public void onLocationChanged(Location location) {
                    moveCamera(location);
                }

                public void onStatusChanged(String provider, int status, Bundle extras) {
                }

                public void onProviderEnabled(String provider) {
                }

                public void onProviderDisabled(String provider) {
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
                checkPermission();
            }
        });

    }
}
