package com.kai.video.bean;


import com.kai.video.sniffing.Util;
import com.kai.video.view.SniffingWebViewClient;
import com.tencent.smtt.export.external.interfaces.JsResult;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebView;

public class SniffingWebChromeClient extends WebChromeClient {

    private SniffingWebViewClient mClient;

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
