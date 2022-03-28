package com.example.Horse_App.Fragments;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

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

public class MapsFragment extends SupportMapFragment implements OnMapReadyCallback {

    private View fragmentView;
    private GoogleMap mMap;
    private final String positions;
    private String newPoints;
    private String[] pointLocation;

    public MapsFragment(String positions) {
        this.positions = positions;
    }

    private void initGoogleMap() {
        getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull GoogleMap googleMap) {
                pointLocation = null;
                mMap = googleMap;
                List<LatLng> path = new ArrayList<LatLng>();
                pointLocation = positions.split("/");

                for (int i = 0; i < pointLocation.length - 1; i += 2) {
                    LatLng newPoint = new LatLng(Double.parseDouble(pointLocation[i]), Double.parseDouble(pointLocation[i + 1]));
                    path.add(newPoint);
                }

                mMap.addMarker(new MarkerOptions().position(path.get(0)).title("Start"));
                mMap.addMarker(new MarkerOptions().position(path.get(path.size() - 1)).title("End"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(path.get(0), 13.0f));
                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

                PolylineOptions opts = new PolylineOptions().addAll(path).color(Color.BLUE).width(7);
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
    }

}