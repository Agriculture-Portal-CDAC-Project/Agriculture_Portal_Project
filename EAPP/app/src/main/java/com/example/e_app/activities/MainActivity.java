package com.example.e_app.activities;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;

import com.example.e_app.API.RetrofitClient;
import com.example.e_app.R;
import com.example.e_app.utility.AgriculturePortalConstant;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.e_app.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        //,R.id.nav_offers,R.id.nav_new_products,R.id.nav_my_orders
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_category,R.id.nav_profile,R.id.nav_my_carts)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//
//        if (item.getTitle().equals("Logout")) {
//
//           getSharedPreferences(AgriculturePortalConstant.SHARED_PREFERENCE_FILE_NAME,MODE_PRIVATE)
//                   .edit().putBoolean(AgriculturePortalConstant.LOGIN_STATUS,false)
//                   .apply();
//          finish();
//          return true;
//        }
//        return super.onOptionsItemSelected(item);
//
//
//
//
//    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // to check for if remeber me is checked then only show the logout menu button
//        if (getSharedPreferences(AgriculturePortalConstant.SHARED_PREFERENCE_FILE_NAME, MODE_PRIVATE)
//                .getBoolean(AgriculturePortalConstant.LOGIN_STATUS, false))
//            menu.add("logout").setIcon(R.drawable.logout).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        getSharedPreferences(AgriculturePortalConstant.SHARED_PREFERENCE_FILE_NAME, MODE_PRIVATE)
//                .edit()
//                .putBoolean(AgriculturePortalConstant.LOGIN_STATUS, false)
//                .apply();
//        finish();
//        return super.onOptionsItemSelected(item);
//    }
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}