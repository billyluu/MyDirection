package com.billylu.mydirection;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.billylu.mydirection.bean.PositionBean;
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
    private PositionBean positionBean;
    private LatLng latLng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_layout);

//        setToolbar(R.id.toolbar, true, R.menu.toolbar_menu);
//        setMenuAddButton(false);
//        setMenuPoint(false);

        Bundle bundle = getIntent().getExtras();
        positionBean = (PositionBean) bundle.getSerializable("bean");
        latLng = getLatlng(positionBean);


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        if (latLng != null) {
            mapFragment.getMapAsync(this);
        }

    }

    private LatLng getLatlng(PositionBean bean) {
        double lat = bean.getLat();
        double lng = bean.getLng();
        
        return new LatLng(lat, lng);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera

        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.addMarker(new MarkerOptions().position(latLng).title(positionBean.getAddress()));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15.0f));
    }
}
