package com.example.xsefety.First_Activity;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.example.xsefety.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.xsefety.databinding.ActivityMapsBinding;
import static java.lang.Math.abs;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    private final int FIND_PERMISSION_CODE = 1;
    Location currentLocation;
    FusedLocationProviderClient fusedLocationProviderClient;


    //SearchView for map searchview
    private SearchView mapSearchView;

    boolean b=false,midnapore=false,kgp=false,debra=false,kolaghat=false,panskura=false,pingla=false,daspur=false,ghatal=false,keshpur=false,tamluk=false;

    ImageButton imageButton;
    double distance=0.18;


    Handler handler;
    Runnable runnable;

    LatLng latLngKeshpur,latLngKgp,latLngMidnapur,latLngKolaghat,latLngPanskura,latLngGhatal,latLngPingla,latLngTamluk,latLngDebra,latLngDaspur;

    Button btn;
    int c=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

            location_calculator location_calculator=new location_calculator();

            btn=(Button) findViewById(R.id.btn);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (c)
                    {
                        case 0: mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                            btn.setText("satellite");
                            c++;
                            break;
                        case 1:mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                            btn.setText("Terrain");
                            c++;
                            break;
                        case 2: mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                            btn.setText("Hybrid");
                            c++;
                            break;
                        case 3:mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                            btn.setText("Normal");
                            c=0;
                            break;
                    }
                }
            });

            imageButton=(ImageButton) findViewById(R.id.police_button);

            mapSearchView = (SearchView) (findViewById(R.id.searchView));
            mapSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    Geocoder geocoder=new Geocoder(getApplicationContext());
                    List<Address> list=new ArrayList<>();
                    try {
                        list=geocoder.getFromLocationName(query,1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if(list.size()>0)
                    {
                        Address address=list.get(0);
                        LatLng latLngSearch=new LatLng(address.getLatitude(),address.getLongitude());
                        mMap.addMarker(new MarkerOptions().position(latLngSearch).title("Hi"));
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLngSearch,16));
                    }

                    return true;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    return false;
                }
            });


            //current location
            fusedLocationProviderClient= LocationServices.getFusedLocationProviderClient(this);
            handler=new Handler();
            handler.postDelayed(runnable=new Runnable() {
                @Override
                public void run() {
                    handler.postDelayed(runnable,2000);
                    getLastLocation();

                }
            },2000);
        }
        private void getLastLocation()
        {
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},FIND_PERMISSION_CODE);
            }
            else {
                try {
                    Task<Location> task = fusedLocationProviderClient.getLastLocation();
                    task.addOnSuccessListener(new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            if (location != null) {
                                currentLocation = location;
                                location_calculator.setLat(currentLocation.getLatitude());
                                location_calculator.setLng(currentLocation.getLongitude());
                                SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
                                if(mapFragment==null)
                                    return;
                                mapFragment.getMapAsync(MapsActivity.this);
                            }

                            if (mMap != null)
                            {
                                mMap.clear();
                                LatLng sydney = new LatLng(currentLocation.getLatitude(),currentLocation.getLongitude());
                                mMap.addMarker(new MarkerOptions()
                                        .position(sydney)
                                        .title("mylocation")
                                        .flat(true));
                                mMap.getUiSettings().setZoomControlsEnabled(true);
                                mMap.getUiSettings().setMyLocationButtonEnabled(true);
                                mMap.getUiSettings().setCompassEnabled(true);
                                mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
                                if (ActivityCompat.checkSelfPermission(MapsActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                                    return;
                                }
                                mMap.setMyLocationEnabled(true);
                            }
                        }
                    });
                }catch (Exception e)
                {
                    finish();
                }
            }
        }



        @Override
        public void onMapReady(@NonNull GoogleMap googleMap) {
            mMap = googleMap;

            if(b)
            {

                latLngKeshpur=new LatLng(22.5558,87.4613);
                if(keshpur)
                {
                    MarkerOptions markerOptions4=new MarkerOptions().position(latLngKeshpur).title("Keshpur police station") .icon(setIcom(MapsActivity.this,R.drawable.baseline_accessibility));;
                    mMap.addMarker(markerOptions4);
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLngKeshpur,16));
                }


                latLngKgp=new LatLng(22.3380,87.3099);
                if(kgp)
                {
                    MarkerOptions markerOptions5=new MarkerOptions().position(latLngKgp).title("Kharagpur police station") .icon(setIcom(MapsActivity.this,R.drawable.baseline_accessibility));;
                    mMap.addMarker(markerOptions5);
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLngKgp,16));
                }

                latLngMidnapur=new LatLng(22.430889,87.321487);
                if(midnapore)
                {
                    MarkerOptions markerOptions7=new MarkerOptions().position(latLngMidnapur).title("Midnapur police station") .icon(setIcom(MapsActivity.this,R.drawable.baseline_accessibility));;
                    mMap.addMarker(markerOptions7);
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLngMidnapur,16));
                }

                latLngGhatal=new LatLng(22.6648,87.7282);
                if(ghatal)
                {
                    MarkerOptions markerOptions9=new MarkerOptions().position(latLngGhatal).title("Ghatal police station").icon(setIcom(MapsActivity.this,R.drawable.baseline_accessibility));;
                    mMap.addMarker(markerOptions9);
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(latLngGhatal));
                }

                latLngPingla=new LatLng(22.2780,87.5862);
                if(pingla)
                {
                    MarkerOptions markerOptions10=new MarkerOptions().position(latLngPingla).title("Pingla police station").icon(setIcom(MapsActivity.this,R.drawable.baseline_accessibility));;
                    mMap.addMarker(markerOptions10);
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(latLngPingla));
                }

                latLngTamluk=new LatLng(22.3,87.92);
                if(tamluk)
                {
                    MarkerOptions markerOptions6=new MarkerOptions().position(latLngTamluk).title("Tamluk police station") .icon(setIcom(MapsActivity.this,R.drawable.baseline_accessibility));;
                    mMap.addMarker(markerOptions6);
                    mMap.animateCamera(CameraUpdateFactory.newLatLng(latLngTamluk));
                }

                latLngDaspur=new LatLng(22.6074,87.7232);
                if(daspur)
                {
                    MarkerOptions markerOptions8=new MarkerOptions().position(latLngDaspur).title("Daspur police station").icon(setIcom(MapsActivity.this,R.drawable.baseline_accessibility));;
                    mMap.addMarker(markerOptions8);
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(latLngDaspur));
                }

                latLngDebra=new LatLng(22.3630,87.5519);
                if(debra)
                {
                    MarkerOptions markerOptions1=new MarkerOptions().position(latLngDebra).title("Debra police station").icon(setIcom(MapsActivity.this,R.drawable.baseline_accessibility));;
                    mMap.addMarker(markerOptions1);
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(latLngDebra));
                }

                latLngKolaghat=new LatLng(22.4354,87.8505);
                if(kolaghat)
                {
                    MarkerOptions markerOptions3=new MarkerOptions().position(latLngKolaghat).title("Kolaghat police station") .icon(setIcom(MapsActivity.this,R.drawable.baseline_accessibility));;
                    mMap.addMarker(markerOptions3);
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLngKolaghat,16));
                }

                latLngPanskura=new LatLng(22.3970,87.7139);
                if(panskura)
                {
                    MarkerOptions markerOptions2=new MarkerOptions()
                            .position(latLngPanskura)
                            .title("Panskura police station");
                    // .icon(setIcom(MapsActivity.this,R.drawable.baseline_accessibility_new_24));
                    mMap.addMarker(markerOptions2);
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(latLngPanskura));
                }

                double lat=currentLocation.getLatitude();
                double log=currentLocation.getLongitude();

                if(abs(lat-((double) latLngDebra.latitude))<=distance && abs(log-((double) latLngDebra.longitude))<=distance)
                {
                    debra=true;
                }

                if(abs(lat-((double) latLngKgp.latitude))<=distance && abs(log-((double) latLngKgp.longitude))<=distance)
                {
                    kgp=true;
                }

                if(abs(lat-((double) latLngMidnapur.latitude))<=distance && abs(log-((double) latLngMidnapur.longitude))<=distance)
                {
                    midnapore=true;
                }
                if(abs(lat-((double) latLngPanskura.latitude))<=distance && abs(log-((double) latLngPanskura.longitude))<=distance)
                {
                    panskura=true;
                }
                if(abs(lat-((double) latLngKolaghat.latitude))<=distance && abs(log-((double) latLngKolaghat.longitude))<=distance)
                {
                    kolaghat=true;
                }
                if(abs(lat-((double) latLngKeshpur.latitude))<=distance && abs(log-((double) latLngKeshpur.longitude))<=distance)
                {
                    keshpur=true;
                }
                if(abs(lat-((double) latLngGhatal.latitude))<=distance && abs(log-((double) latLngGhatal.longitude))<=distance)
                {
                    ghatal=true;
                }
                if(abs(lat-((double) latLngDaspur.latitude))<=distance && abs(log-((double) latLngDaspur.longitude))<=distance)
                {
                    daspur=true;
                }
                if(abs(lat-((double) latLngTamluk.latitude))<=distance && abs(log-((double) latLngTamluk.longitude))<=distance)
                {
                    tamluk=true;
                }


            }
            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(b)
                    {
                        b=false;
                        Toast.makeText(getApplicationContext(),"wait for hide police station",Toast.LENGTH_LONG).show();

                    }
                    else
                    {
                        b=true;
                        Toast.makeText(getApplicationContext(),"wait for show police station",Toast.LENGTH_LONG).show();

                    }
                }
            });

            //Dependency to find current location (implementation("com.google.android.gms:play-services-location:21.3.0"))
        }


        public BitmapDescriptor setIcom(Activity context,int drawable)
        {
            Drawable drawable1=ActivityCompat.getDrawable(context,drawable);
            drawable1.setBounds(0,0,drawable1.getIntrinsicWidth(),drawable1.getIntrinsicHeight());
            Bitmap bitmap=Bitmap.createBitmap(drawable1.getIntrinsicWidth(),drawable1.getIntrinsicHeight(),Bitmap.Config.ARGB_8888);
            Canvas canvas=new Canvas((bitmap));
            drawable1.draw(canvas);
            return BitmapDescriptorFactory.fromBitmap(bitmap);
        }


            @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults, int deviceId) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults, deviceId);
        if(requestCode==FIND_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                getLastLocation();
            }
            else
            {
                Toast.makeText(this,"Location permission is denied, please allow the permission to access",Toast.LENGTH_LONG).show();
            }
        }
    }


//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        Intent intent = new Intent(MapsActivity.this, Home_page_Activity.class);
//        startActivity(intent);
//
//    }
}


