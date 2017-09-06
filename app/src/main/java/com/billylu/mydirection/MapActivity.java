package com.billylu.mydirection;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.billylu.mydirection.model.TranslatePosition;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


/**
 * Created by billylu on 2017/8/28.
 */

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private LatLng latLng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_layout);

//        setToolbar(R.id.toolbar, true, R.menu.toolbar_menu);
//        setMenuAddButton(false);
//        setMenuPoint(false);

        String[] result = getIntent().getStringArrayExtra("latlng");
        Log.i("result", result[0] + "," + result[1]);
        latLng = getLatlng(result);


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        if (latLng != null) {
            mapFragment.getMapAsync(this);
        }

    }

    private LatLng getLatlng(String[] latlng) {
        double lat = Double.valueOf(latlng[0]);
        double lng = Double.valueOf(latlng[1]);
        
        return new LatLng(lat, lng);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera

        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.addMarker(new MarkerOptions().position(latLng).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15.0f));
    }
}
