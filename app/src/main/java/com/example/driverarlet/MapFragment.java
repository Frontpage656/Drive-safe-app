package com.example.driverarlet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view =inflater.inflate(R.layout.frame_map, container, false);
        SupportMapFragment supportMapFragment = (SupportMapFragment)
                getChildFragmentManager().findFragmentById(R.id.google_map_frg);

        String[] titleList = {"Davina Khanan (3km)","Mbalizi","kabwe","Uyole"};

        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull GoogleMap googleMap) {
                LatLng location1 = new LatLng(-8.9413541,33.4139132);
                LatLng location2 = new LatLng(-8.9372363,33.3539826);
                LatLng location3 = new LatLng(-8.9275529,33.2387021);
                LatLng location4 = new LatLng(-8.9114679,33.4605135);

                // creating array list for adding all our locations.
                ArrayList<LatLng> locationArrayList = new ArrayList<>();
                locationArrayList.add(location1);
                locationArrayList.add(location2);
                locationArrayList.add(location3);
                locationArrayList.add(location4);

                for (int i=0; i<=locationArrayList.size()-1; i++){
                    googleMap.addMarker(new MarkerOptions()
                            .position(locationArrayList.get(i))
                            .title(titleList[i]));
                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(locationArrayList.get(i),10));
                }
            }
        });

        return view;
    }
}
