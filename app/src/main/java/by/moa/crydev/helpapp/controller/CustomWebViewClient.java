package by.moa.crydev.helpapp.controller;

import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by Fedor on 15/6/16.
 */
public class CustomWebViewClient extends WebViewClient {

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {

        view.loadUrl(url);
        return true;
    }

}
