package com.example.appfotos;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.example.appfotos.fragments.EspeciasModelFragment;
import com.example.appfotos.fragments.FotoFragment;
import com.example.appfotos.fragments.MapFragment;
import com.example.appfotos.model.EspeciaModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements EspeciasModelFragment.OnListFragmentInteractionListener {


    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    LocationManager mLocationManager;

    public static Location localizacion;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_mapa:

                    setFragment(new MapFragment());
                    return true;
                case R.id.navigation_foto:

                    setFragment(new FotoFragment());
                    return true;
                case R.id.navigation_lista:
                    setFragment(new EspeciasModelFragment());
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ApplicationPreferences.init(this);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        checkLocationPermission();

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 100, 1, mLocationListener);
        }
    }

    private void setFragment(Fragment miFragment) {

        FragmentManager FM = getSupportFragmentManager();
        FragmentTransaction FT = FM.beginTransaction();

        FT.replace(R.id.main_container, miFragment);
        FT.commit();
    }

    @Override
    public void onListFragmentInteraction(EspeciaModel item) {
            Log.e("ONLISTFRAGMENT", "1");
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        Log.e("Pointer", "1");

    }
    @Override
    public void onPause() {
        super.onPause();

        // Para que no esté funcionando en background
        mLocationManager.removeUpdates(mLocationListener);

    }


    private void checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                new AlertDialog.Builder(getApplicationContext())
                        .setTitle("Location Permission Needed")
                        .setMessage("This app needs the Location permission, please accept to use location functionality")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                //ActivityCompat.requestPermissions(this,
                                //new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                //MY_PERMISSIONS_REQUEST_LOCATION );
                            }
                        })
                        .create()
                        .show();
            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION );
            }
        }
    }




    private final LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(final Location location) {
            Toast.makeText(getApplicationContext(), "onLocationChanged" , Toast.LENGTH_SHORT).show();
            localizacion = location;
           // text_latitud.setText(Double.toString(location.getLatitude()));
           // text_longitud.setText(Double.toString(location.getLongitude()));
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            Toast.makeText(getApplicationContext(), "onStatusChanged" , Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onProviderEnabled(String provider) {
            Toast.makeText(getApplicationContext(), "onProviderEnabled" , Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onProviderDisabled(String provider) {
            Toast.makeText(getApplicationContext(), "onProviderDisabled" , Toast.LENGTH_SHORT).show();
        }
    };

}
