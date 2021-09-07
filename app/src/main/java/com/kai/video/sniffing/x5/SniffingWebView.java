package com.kai.video.sniffing.x5;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;

import com.tencent.smtt.sdk.CookieManager;
import com.tencent.smtt.sdk.CookieSyncManager;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;

@SuppressLint({"SetJavaScriptEnabled", "ObsoleteSdkInt"})
public class SniffingWebView extends WebView {

    public SniffingWebView(Context context) {
        this(context, null);
    }

    public SniffingWebView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SniffingWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initWebSetting(context);
    }

    private void initWebSetting(Context context) {
        clearFocus();
        WebSettings mWebSettings = getSettings();
        mWebSettings.setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.15; rv:70.0) Gecko/20100101 Firefox/70.0");
        mWebSettings.setDefaultTextEncodingName("utf-8");
        mWebSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        mWebSettings.setUseWideViewPort(true);
        mWebSettings.setAllowFileAccess(true);
        mWebSettings.setSupportZoom(true);
        mWebSettings.setAllowContentAccess(true);
        mWebSettings.setJavaScriptEnabled(true);
        mWebSettings.setDomStorageEnabled(true);
        mWebSettings.setSupportMultipleWindows(true);
        mWebSettings.setMediaPlaybackRequiresUserGesture(false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mWebSettings.setMixedContentMode(0);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            mWebSettings.setMediaPlaybackRequiresUserGesture(true);
        }
        if (Build.VERSION.SDK_INT >= 16) {
            mWebSettings.setAllowFileAccessFromFileURLs(true);
            mWebSettings.setAllowUniversalAccessFromFileURLs(true);
        }
        mWebSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        mWebSettings.setLoadsImagesAutomatically(true);
        mWebSettings.setAppCacheEnabled(true);
        mWebSettings.setAppCachePath(context.getCacheDir().getAbsolutePath());
        mWebSettings.setDatabaseEnabled(true);
        mWebSettings.setGeolocationDatabasePath(context.getDir("database", 0).getPath());
        mWebSettings.setGeolocationEnabled(true);
        CookieManager instance = CookieManager.getInstance();
        if (Build.VERSION.SDK_INT < 21) {
            CookieSyncManager.createInstance(context.getApplicationContext());
        }
        instance.setAcceptCookie(true);
        if (Build.VERSION.SDK_INT >= 21) {
            instance.setAcceptThirdPartyCookies(this, true);
        }
    }

}
