package by.moa.crydev.helpapp.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import by.moa.crydev.helpapp.R;

public class LauncherActivity extends Activity {

    public static final String LOG_TAG = "LauncherActivity";

    private Handler mHandler = new Handler();
    private long    showTime = 2000L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        final Intent intent = new Intent(this, MainActivity.class);

        mHandler.postDelayed(new Runnable() {

            @Override
            public void run() {
                startActivity(intent);
                finish();
            }

        }, showTime);
    }
}
