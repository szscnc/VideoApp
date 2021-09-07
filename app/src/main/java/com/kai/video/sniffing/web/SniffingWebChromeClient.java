package com.kai.video.sniffing.web;

import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.kai.video.sniffing.Util;


public class SniffingWebChromeClient extends WebChromeClient {

    private com.kai.video.sniffing.web.SniffingWebViewClient mClient;

    public SniffingWebChromeClient(SniffingWebViewClient mClient) {
        this.mClient = mClient;
    }

    @Override
    public boolean onJsConfirm(WebView webView, String s, String s1, JsResult jsResult) {
        if (s1.contains(Util.HTMLFLAG)) {
            mClient.parserHtml(webView, s, s1);
            jsResult.cancel();
            return true;
        }
        return super.onJsConfirm(webView, s, s1, jsResult);
    }

    @Override
    public boolean onJsAlert(WebView webView, String s, String s1, JsResult jsResult) {
        if (s1.contains(Util.HTMLFLAG)) {
            return onJsConfirm(webView, s, s1, jsResult);
        }
        return super.onJsAlert(webView, s, s1, jsResult);
    }

}
