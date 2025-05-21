package com.example.lostfoundapp;

import android.os.Bundle;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        dbHelper = new DatabaseHelper(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        ArrayList<Item> items = dbHelper.getAllItems();
        if (items.isEmpty()) {
            Toast.makeText(this, "No items found in database", Toast.LENGTH_SHORT).show();
            return;
        }

        boolean markerAdded = false;

        for (Item item : items) {
            double lat = item.getLatitude();
            double lng = item.getLongitude();

            if (lat != 0.0 && lng != 0.0) {
                LatLng position = new LatLng(lat, lng);
                mMap.addMarker(new MarkerOptions()
                        .position(position)
                        .title(item.getTitle())
                        .snippet("Status: " + item.getStatus()));

                if (!markerAdded) {
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(position, 12f));
                    markerAdded = true;
                }
            }
        }

        if (!markerAdded) {
            Toast.makeText(this, "No valid map locations to display", Toast.LENGTH_SHORT).show();
        }
    }
}
