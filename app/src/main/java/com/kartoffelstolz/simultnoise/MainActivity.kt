package com.kartoffelstolz.simultnoise

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.webkit.WebViewAssetLoader

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        val webView: WebView = findViewById(R.id.activity_main_webview);
        val webSettings: WebSettings = webView.getSettings()
        webSettings.javaScriptEnabled = true
        webSettings.domStorageEnabled = true
        webSettings.defaultTextEncodingName = "utf-8";

        val assetLoader = WebViewAssetLoader.Builder()
            .addPathHandler("/assets/", WebViewAssetLoader.AssetsPathHandler(this))
            .build();

        webView.webViewClient = object : WebViewClient() {
            override fun shouldInterceptRequest(
                view: WebView,
                request: WebResourceRequest
            ): WebResourceResponse? {
                return assetLoader.shouldInterceptRequest(request.url);
            }
        }

        webView.loadUrl("https://appassets.androidplatform.net/assets/index.html");
    }
}