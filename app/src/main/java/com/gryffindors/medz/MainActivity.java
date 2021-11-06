package com.gryffindors.medz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.gryffindors.medz.fragments.AddFragment;
import com.gryffindors.medz.fragments.HomeFragment;
import com.gryffindors.medz.fragments.ProfileFragment;

public class MainActivity extends AppCompatActivity  {

    final Fragment fragment1 = new HomeFragment();
    final Fragment fragment2 = new AddFragment();
    final Fragment fragment3 = new ProfileFragment();
    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = fragment1;
    FloatingActionButton addfab;
    public static Context contextOfApplication;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        navigation.setBackground(null);

        contextOfApplication = getApplicationContext();

        fm.beginTransaction().add(R.id.main_container, fragment3, "3").hide(fragment3).commit();
        fm.beginTransaction().add(R.id.main_container, fragment2, "2").hide(fragment2).commit();
        fm.beginTransaction().add(R.id.main_container,fragment1, "1").commit();


        addfab= findViewById(R.id.fab);
        addfab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fm.beginTransaction().hide(active).show(fragment2).commit();
                active = fragment2;
            }
        });


    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.memories:
                    item.setIcon(R.drawable.ic_launcher_background);
                    fm.beginTransaction().hide(active).show(fragment1).commit();
                    active = fragment1;
                    return true;

                case R.id.fab:

                    fm.beginTransaction().hide(active).show(fragment2).commit();
                    active = fragment2;
                    return true;

                case R.id.profile:
                    item.setIcon(R.drawable.ic_launcher_background);
                    fm.beginTransaction().hide(active).show(fragment3).commit();
                    active = fragment3;
                    return true;
            }
            return false;
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bottom_nav_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();



        return super.onOptionsItemSelected(item);
    }
    public static Context getContextOfApplication()
    {
        return contextOfApplication;
    }
}