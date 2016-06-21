package by.moa.crydev.helpapp.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import by.moa.crydev.helpapp.R;
import by.moa.crydev.helpapp.activities.fragments.PlaceholderFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    public static final String LOG_TAG = "MainActivity";
    public static final String INQUIRY_NUMBER = "80172263239";
    public static final String RECEPTION_NUMBER = "80172263299";
    public static final String LATITUDE = "53.885585";
    public static final String LONGITUDE = "27.520452";

    private NavigationView mNavigationView;
    private boolean exit = false;
    private long exitTime = 3000L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        if (mNavigationView != null) {
            mNavigationView.setNavigationItemSelectedListener(this);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        if (drawer != null) {
            drawer.setDrawerListener(toggle);
        }
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.coordinator_layout, new PlaceholderFragment())
                    .commit();
        }
    }

    @Override
    public void onBackPressed() {

        if (exit) {
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            if (drawer != null && drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                super.onBackPressed();
            }
            finish();
        } else {
            Snackbar.make(mNavigationView, getResources().getString(R.string.exit_app_snack),
                    Snackbar.LENGTH_SHORT)
                    .setAction("Action", null)
                    .show();

            exit = true;

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, exitTime);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
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
        int id = item.getItemId();

        if (id == R.id.nav_facebook) {
            Intent intent = new Intent(this, FacebookActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_twitter) {
            Intent intent = new Intent(this, TwitterActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_inquiry) {
            startDialActivity(INQUIRY_NUMBER);
        } else if (id == R.id.nav_reception) {
            startDialActivity(RECEPTION_NUMBER);
        } else if (id == R.id.nav_map) {
            openPreferredLocationInMap();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer != null) {
            drawer.closeDrawer(GravityCompat.START);
        }
        return true;
    }

    private void startDialActivity(String phone){

        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phone));
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {

        int id = v.getId();
        switch (id) {
            case R.id.fab:
                Snackbar.make(v, "No action for now", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null)
                        .show();
                break;
        }
    }

    private void openPreferredLocationInMap() {

        String label = getResources().getString(R.string.address_street);
        Uri uri = Uri.parse("geo:" + LATITUDE + "," + LONGITUDE + "?q=" + LATITUDE + ","
                + LONGITUDE + "(" + label + ")");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Snackbar.make(mNavigationView, getResources().getString(R.string.no_maps_snack),
                    Snackbar.LENGTH_LONG)
                    .setAction("Action", null)
                    .show();
            Log.d(LOG_TAG, "Couldn't call " + LATITUDE + " " +  LONGITUDE
                    + ", no receiving apps installed!");
        }
    }

}
