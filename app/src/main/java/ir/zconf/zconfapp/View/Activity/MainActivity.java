package ir.zconf.zconfapp.View.Activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import ir.zconf.zconfapp.R;
import ir.zconf.zconfapp.View.Fragment.ContentFragment;

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
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        if(menuItem.isChecked()){
            menuItem.setChecked(false);
        } else {
            menuItem.setChecked(true);
        }

        drawerLayout.closeDrawers();
        switch (menuItem.getItemId()){

            case R.id.thursday:
                Toast.makeText(getApplicationContext(),"Inbox Selected",Toast.LENGTH_SHORT).show();
                ContentFragment fragment = new ContentFragment();
                android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame,fragment);
                fragmentTransaction.commit();
                return true;


        }
        return true;
    }
}