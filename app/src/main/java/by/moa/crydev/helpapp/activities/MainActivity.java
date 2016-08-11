package by.moa.crydev.helpapp.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import by.moa.crydev.helpapp.R;
import by.moa.crydev.helpapp.activities.fragments.PlaceholderFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static final String LOG_TAG = "MainActivity";
    public static final String INQUIRY_NUMBER = "80172263239";
    public static final String RECEPTION_NUMBER = "80172263299";
    public static final String LATITUDE = "53.885585";
    public static final String LONGITUDE = "27.520452";
    public static final String REQUISITES = "ЦБУ № 537 ОАО \"Белинвестбанк\" г. Минск код 739 \n" +
            "Р/С 3012002133710 \n" +
            "УНП 600013237 \n" +
            "ОКПО 37402696 \n" +
            "МФО 153001739 ";

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
                    .setAction(getResources().getString(R.string.snackbar_exit), new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            finish();
                        }
                    })
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
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_twitter) {
            Intent intent = new Intent(this, TwitterActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_inquiry) {
            AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                    .setTitle(getResources().getString(R.string.drawer_number_inquiry))
                    .setMessage(getResources().getString(R.string.dialog_call_number)
                            + " " + INQUIRY_NUMBER)
                    .setPositiveButton(getResources().getString(R.string.dialog_call),
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    startDialActivity(INQUIRY_NUMBER);
                                }
                            })
                    .setNegativeButton(getResources().getString(R.string.dialog_close),
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    dialog.dismiss();
                                }
                            })
                    .create();
            dialog.show();

        } else if (id == R.id.nav_reception) {
            AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                    .setTitle(getResources().getString(R.string.drawer_number_reception))
                    .setMessage(getResources().getString(R.string.dialog_call_number)
                            + " " + RECEPTION_NUMBER)
                    .setPositiveButton(getResources().getString(R.string.dialog_call),
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    startDialActivity(RECEPTION_NUMBER);
                                }
                            })
                    .setNegativeButton(getResources().getString(R.string.dialog_close),
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    dialog.dismiss();
                                }
                            })
                    .create();
            dialog.show();

        } else if (id == R.id.nav_map) {
            AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                    .setTitle(getResources().getString(R.string.drawer_map))
                    .setMessage(getResources().getString(R.string.address_street))
                    .setPositiveButton(getResources().getString(R.string.dialog_show),
                            new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            openPreferredLocationInMap();
                        }
                    })
                    .setNegativeButton(getResources().getString(R.string.dialog_close),
                            new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            dialog.dismiss();
                        }
                    })
                    .create();
            dialog.show();

        } else if (id == R.id.nav_email) {
            AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                    .setTitle(getResources().getString(R.string.drawer_email))
                    .setMessage(getResources().getString(R.string.chooser_text_email))
                    .setPositiveButton(getResources().getString(R.string.dialog_compose),
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    sendEmail();
                                }
                            })
                    .setNegativeButton(getResources().getString(R.string.dialog_close),
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    dialog.dismiss();
                                }
                            })
                    .create();
            dialog.show();

        } else if (id == R.id.nav_requisites) {
            AlertDialog dialog = new AlertDialog.Builder(MainActivity.this).create();
            dialog.setTitle(getResources().getString(R.string.drawer_requisites));
            dialog.setMessage(REQUISITES);
            dialog.setButton(AlertDialog.BUTTON_NEUTRAL,
                    getResources().getString(R.string.dialog_close),
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            dialog.show();
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

    private void sendEmail() {

        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:"
                + getResources().getString(R.string.email_company)));
        startActivity(Intent.createChooser(emailIntent,
                getResources().getString(R.string.chooser_text_email)));
    }

}
