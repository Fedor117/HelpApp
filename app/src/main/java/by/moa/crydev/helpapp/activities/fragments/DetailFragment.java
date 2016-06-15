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
import controller.CustomWebViewClient;

public class DetailFragment extends Fragment {

    public static final String LOG_TAG = "DetailFragment";
    public static final String RAW_ASSET_DIR = "file:///android_res/raw/";

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

        // The detail Activity called via intent.  Inspect the intent for forecast data.
        Intent intent = getActivity().getIntent();
        String serviceStr = "";
        if (intent != null && intent.hasExtra(Intent.EXTRA_TEXT)) {
            serviceStr = intent.getStringExtra(Intent.EXTRA_TEXT);
        }
        String path = findHtml(serviceStr);

        mWebView = (WebView) rootView.findViewById(R.id.webView);
        mWebView.setWebViewClient(new CustomWebViewClient());
        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.getSettings().setUseWideViewPort(true);
        mWebView.loadUrl(path);

        return rootView;
    }

    private String findHtml(String serviceStr) {

        String responsePath = "";
        switch (serviceStr) {
            case "Государственная регистрация создания, изменения, прекращения существования, "
                    + "а также возникновения, перехода, прекращения прав на недвижимое "
                    + "имущество и сделок с ним":
                responsePath = RAW_ASSET_DIR.concat("state_reg.html");
                break;
            case "Техническая инвентаризация сооружений":
                responsePath = RAW_ASSET_DIR.concat("tech_inv_buildings.html");
                break;
            case "Землеустроительные и геодезические работы":
                responsePath = RAW_ASSET_DIR.concat("land_management.html");
                break;
            case "Независимая оценка имущества":
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
