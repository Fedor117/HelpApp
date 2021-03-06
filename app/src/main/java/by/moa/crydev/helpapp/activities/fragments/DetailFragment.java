package by.moa.crydev.helpapp.activities.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import by.moa.crydev.helpapp.R;
import by.moa.crydev.helpapp.controller.CustomWebViewClient;

public class DetailFragment extends Fragment {

    public static final String LOG_TAG = "DetailFragment";
    public static final String RAW_ASSET_DIR = "file:///android_res/raw/";
    public static final String STATE_REG = "Государственная регистрация создания, изменения, прекращения существования, "
            + "а также возникновения, перехода, прекращения прав на недвижимое "
            + "имущество и сделок с ним";
    public static final String TECH_INV_BUILDINGS = "Техническая инвентаризация сооружений";
    public static final String LAND_MANAGEMENT = "Землеустроительные и геодезические работы";
    public static final String ASSESSMENT = "Независимая оценка имущества";

    private static WebView mWebView;

    public DetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);

        Intent intent = getActivity().getIntent();
        String serviceStr = "";
        if (intent != null && intent.hasExtra(Intent.EXTRA_TEXT)) {
            serviceStr = intent.getStringExtra(Intent.EXTRA_TEXT);
        }
        String path = findHtml(serviceStr);

        mWebView = (WebView) rootView.findViewById(R.id.webView);
        mWebView.setWebViewClient(new CustomWebViewClient());
        mWebView.loadUrl(path);

        return rootView;
    }

    private String findHtml(String serviceStr) {

        String responsePath = "";
        switch (serviceStr) {
            case STATE_REG:
                responsePath = RAW_ASSET_DIR.concat("state_reg.html");
                break;
            case TECH_INV_BUILDINGS:
                responsePath = RAW_ASSET_DIR.concat("tech_inv_buildings.html");
                break;
            case LAND_MANAGEMENT:
                responsePath = RAW_ASSET_DIR.concat("land_management.html");
                break;
            case ASSESSMENT:
                responsePath = RAW_ASSET_DIR.concat("assessment.html");
                break;
        }
        Log.d(LOG_TAG, responsePath);

        return responsePath;
    }

    public static WebView getWebView() {
        return mWebView;
    }

}
