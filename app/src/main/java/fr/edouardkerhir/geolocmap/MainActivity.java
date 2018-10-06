package fr.edouardkerhir.geolocmap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

public class MainActivity extends AppCompatActivity {
    final static double TOULOUSE_LATITUDE = 43.6043;
    final static double TOULOUSE_LONGITUDE = 1.4437;
    final static double TOULOUSE_LATITUDE_BORDURES_BOT = 43.565428;
    final static double TOULOUSE_LONGITUDE_BORDURES_BOT = 1.411854;
    final static double TOULOUSE_LATITUDE_BORDURES_TOP = 43.642094;
    final static double TOULOUSE_LONGITUDE_BORDURES_TOP = 1.480995;
    final static int ZOOM_LVL_BY_DEFAULT = 13;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MapView mMap = (MapView) findViewById(R.id.map_view);
        mMap.onCreate(savedInstanceState);
        mMap.onResume();
        createMap(mMap);

    }

    public void createMap(MapView map){
        map.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {

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
                Toast.makeText(MainActivity.this, "Map create", Toast.LENGTH_LONG).show();
            }
        });

    }
}
