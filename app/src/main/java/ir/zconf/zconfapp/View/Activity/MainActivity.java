package ir.zconf.zconfapp.View.Activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import ir.zconf.zconfapp.R;
import ir.zconf.zconfapp.View.Fragment.DayFragment;
import ir.zconf.zconfapp.View.Fragment.PicturesFragment;
import ir.zconf.zconfapp.View.Fragment.TwitterFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open, R.string.close){

            @Override
            public void onDrawerClosed(View drawerView) {
                  super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {

                super.onDrawerOpened(drawerView);
            }
        };

        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        Fragment fragment;
        long unixTime = System.currentTimeMillis() / 1000L;

        if (unixTime < 1442534400) { //09/18/2015 @ 12:00am (UTC)
            fragment = new DayFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame, fragment)
                    .commit();
        } else if (unixTime < 1442620800){ //09/19/2015 @ 12:00am (UTC)
            fragment = new DayFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame, fragment)
                    .commit();
        } else {
            fragment = new PicturesFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        if(menuItem.isChecked()){
            menuItem.setChecked(false);
        } else {
            menuItem.setChecked(true);
        }

        drawerLayout.closeDrawers();

        Fragment fragment;

        switch (menuItem.getItemId()){

            case R.id.thursday:
                fragment = new DayFragment();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame, fragment)
                        .commit();
                return true;

            case R.id.friday:
                fragment = new DayFragment();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame, fragment)
                        .commit();
                return true;

            case R.id.picture:
                fragment = new PicturesFragment();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame, fragment)
                        .commit();
                return true;


            case R.id.twitter:
                fragment = new TwitterFragment();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame, fragment)
                        .commit();
                return true;

            default:
                return false;


        }
    }
}