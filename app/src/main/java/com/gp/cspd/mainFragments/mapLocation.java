package com.gp.cspd.mainFragments;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.gp.cspd.R;

public class mapLocation extends Fragment {

    private GoogleMap mMap;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View root = inflater.inflate(R.layout.activity_map_location, container, false);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mMap=googleMap;

                LatLng CSPDLoc = new LatLng(32.555346, 35.855453);
                mMap.addMarker(new MarkerOptions().position(CSPDLoc).title("الاحوال المدنيه والجوازات اربد"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(CSPDLoc));

                LatLng CSPDLoc1 = new LatLng(32.491764, 35.883213);
                mMap.addMarker(new MarkerOptions().position(CSPDLoc1).title("الاحوال المدنيه والجوازات اربد/الحصن"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(CSPDLoc1));

                LatLng CSPDLoc2 = new LatLng(32.028365, 35.835728);
                mMap.addMarker(new MarkerOptions().position(CSPDLoc2).title("الاحوال المدنيه والجوازات عمان/صويلح"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(CSPDLoc2));

                LatLng CSPDLoc3 = new LatLng(31.881428, 36.830892);
                mMap.addMarker(new MarkerOptions().position(CSPDLoc3).title("الاحوال المدنيه والجوازات الزرقاء"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(CSPDLoc3));

            }
        });


        return root;
    }
}
