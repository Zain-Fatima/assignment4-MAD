package com.example.finalgameapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.net.URI;

public class MainActivity extends AppCompatActivity {

    NavigationView navigationView;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ActionBarDrawerToggle toggle;
//    @Override
//    protected void onResume() {
//        try {
//            super.onResume();
//            // Reset fragment content when the activity resumes
//            Game fragmentGame = (Game) getSupportFragmentManager().findFragmentById(R.id.MainLinearLayout);
//            if (fragmentGame != null) {
//                fragmentGame.resetFragment();
//            }
//        }
//        catch (Exception E)
//        {
//            Toast.makeText(this, ""+ E.toString(), Toast.LENGTH_LONG).show();
//        }
//
//    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Sky Root Game");

        navigationView=findViewById(R.id.nav_view);
        drawerLayout=findViewById(R.id.drawer);

        toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        Game fragmentGame = new Game();
        //Navigation item click listener


        Toast.makeText(getApplicationContext(),"Game is now in session",Toast.LENGTH_LONG).show();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.MainLinearLayout, fragmentGame);
        transaction.commit();
        //hideButton();
        drawerLayout.closeDrawer(GravityCompat.START);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
            {
                switch (menuItem.getItemId())
                {
                    case R.id.nav_book :

                        Toast.makeText(getApplicationContext(),"Game is now in session",Toast.LENGTH_LONG).show();
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.MainLinearLayout, fragmentGame);
                        transaction.commit();

                        //hideButton();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.nav_return :
                        Fragment fragRes= new Results();
                        Toast.makeText(getApplicationContext(),"Results ",Toast.LENGTH_LONG).show();
                        FragmentTransaction transaction2 = getSupportFragmentManager().beginTransaction();
                        transaction2.replace(R.id.MainLinearLayout, fragRes);
                        transaction2.commit();

                        //hideButton();
                        drawerLayout.closeDrawer(GravityCompat.START);

                        break;

                    case R.id.nav_laptop:
                        Toast.makeText(getApplicationContext(), "Opening Github", Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);

                        String url = "https://github.com/AsadNazir/FinalGameApp";
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(url));
                        startActivity(intent);


                    break;


                }

                return true;
            }
        });




    }


    // Method to hide the button
//    private void hideButton() {
//        Button button = findViewById(R.id.button);
//        button.setVisibility(View.GONE);
//    }
//
//    // Method to show the button
//    private void showButton() {
//        Button button = findViewById(R.id.button);
//        button.setVisibility(View.VISIBLE);
//    }
}