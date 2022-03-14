package com.example.Horse_App;

import androidx.annotation.NonNull;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;

public class MapsFragment extends SupportMapFragment implements OnMapReadyCallback{

    private View fragmentView;
    private GoogleMap mMap;

    private void initGoogleMap() {
        getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mMap = googleMap;

                // Add a marker in Sydney and move the camera
                LatLng martigny = new LatLng(46.103592, 7.073867);
                LatLng sierre = new LatLng(46.114392,7.078330);

                List path = new ArrayList<LatLng>();
                path.add(martigny);
                path.add(sierre);

                mMap.addMarker(new MarkerOptions().position(martigny).title("Marker in Martigny"));
                mMap.addMarker(new MarkerOptions().position(sierre));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(martigny,13.0f));
                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

                PolylineOptions opts = new PolylineOptions().addAll(path).color(Color.BLUE).width(5);
                mMap.addPolyline(opts);
            }
        });

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initGoogleMap();
    }
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng martigny = new LatLng(46.103592, 7.073867);
        LatLng sierre = new LatLng(46.114392,7.078330);

        List path = new ArrayList<LatLng>();
        path.add(martigny);
        path.add(sierre);

        mMap.addMarker(new MarkerOptions().position(martigny).title("Marker in Martigny"));
        mMap.addMarker(new MarkerOptions().position(sierre));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(martigny,13.0f));
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

        PolylineOptions opts = new PolylineOptions().addAll(path).color(Color.BLUE).width(5);
        mMap.addPolyline(opts);
    }
}