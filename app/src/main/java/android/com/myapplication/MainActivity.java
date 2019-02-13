package android.com.myapplication;

import android.app.FragmentManager;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //layout variables
        drawerLayout = findViewById(R.id.main_drawer_layout);
        navigationView = findViewById(R.id.main_nav_view);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        //  setSupportActionBar((Toolbar)findViewById(R.id.));
        //  getSupportActionBar().setTitle("Instagram");
        //   getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_home_menu);


        changeFragmentDisplay(R.id.home);


        //listener for navigation view
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem Item) {

                changeFragmentDisplay(Item.getItemId());
                return  true;
            }
        });

    }

    private boolean changeFragmentDisplay(int Item) {


        Fragment fragment = null;

        if (Item == R.id.home) {
            fragment = new HomeFragment();
        }
        if (Item == R.id.search) {
            fragment = new SearchFragment();
        }
        if (Item == R.id.profile) {
            Toast.makeText(MainActivity.this, "Profile", Toast.LENGTH_SHORT).show();
            drawerLayout.closeDrawer(Gravity.START);
            return true;
        }
//                else if (menuItem.getItemId()==R.id.settings)
//                {
//                    Toast.makeText(MainActivity.this,"settings",Toast.LENGTH_SHORT).show();
//                    return true;
//                }
        else if (Item == R.id.log_out) {

SharedPrefrenceManager sharedPrefrenceManager=SharedPrefrenceManager.getInstance(getApplicationContext());

if(sharedPrefrenceManager.isUserLoggedIn())

sharedPrefrenceManager.logUserOut();



            Toast.makeText(MainActivity.this, "log out", Toast.LENGTH_SHORT).show();
            return true;



        } else {
            Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
        }


        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.main_fragment_content, fragment);
            ft.commit();
        }
        return false;


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        boolean isUserLoggedIn = SharedPrefrenceManager.getInstance((getApplicationContext())).isUserLoggedIn();
        if (!isUserLoggedIn) {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));

        } else {

        }
    }
}
