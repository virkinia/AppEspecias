package com.example.appfotos;

import android.os.Bundle;

import androidx.annotation.NonNull;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements EspeciasModelFragment.OnListFragmentInteractionListener {



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
}
