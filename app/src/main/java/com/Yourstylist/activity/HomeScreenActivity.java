package com.Yourstylist.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.Yourstylist.R;
import com.Yourstylist.Utils;
import com.Yourstylist.fragment.ArticlesFragment;
import com.Yourstylist.fragment.GalleryFragment;

public class HomeScreenActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_articles);
        getSupportActionBar().setSubtitle("Articles");
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,new ArticlesFragment()).addToBackStack("articleFragment").commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            Utils.showAppCloseDialog(HomeScreenActivity.this);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        switch (item.getItemId()){
            case R.id.nav_feedback:
                break;
            case R.id.nav_logout:
                SharedPreferences sharedPreferences=getSharedPreferences(Utils.USER_SHARED_PREFERENCE,MODE_PRIVATE);
                sharedPreferences.edit()
                        .remove(Utils.USER_FIRST_NAME)
                        .remove(Utils.USER_LAST_NAME)
                        .remove(Utils.USER_EMAIL)
                        .remove(Utils.USER_MOBILE)
                        .remove(Utils.USER_DEVICE_TOKEN)
                        .apply();
                Intent intent=new Intent(HomeScreenActivity.this,LoginScreenActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.nav_articles:
                getSupportActionBar().setSubtitle("Articles");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,new ArticlesFragment()).addToBackStack("articlesFragment").commit();
                break;
            case R.id.nav_gallery:
                getSupportActionBar().setSubtitle("Gallery");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,new GalleryFragment()).addToBackStack("galleryFragment").commit();
                break;
            case R.id.nav_community:
                break;

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
