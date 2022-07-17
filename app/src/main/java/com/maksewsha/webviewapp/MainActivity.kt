package com.maksewsha.webviewapp

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.webkit.CookieManager
import android.webkit.WebSettings
import android.webkit.WebView
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {

    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        webView = WebView(this)
        setContentView(webView)

        val loaded = loadData(this)
        if(loaded != ""){
            webView.loadUrl(loaded)
        }else{
            webView.loadUrl("https://yandex.ru/")
        }

        webView.settings.javaScriptEnabled = true

        CookieManager.getInstance().setAcceptCookie(true)
        CookieManager.getInstance().acceptThirdPartyCookies(webView)
        webView.settings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
        webView.webViewClient = MyWebViewClient()
    }

    override fun onPause() {
        saveData(
            this,
            webView.url!!
        )
        super.onStop()
    }


    override fun onBackPressed() {

        if (webView.copyBackForwardList().currentIndex > 0) {
            val url = webView.copyBackForwardList()
                .getItemAtIndex(webView.copyBackForwardList().currentIndex - 1).url
            webView.loadUrl(url)
        } else {
            val builder = AlertDialog.Builder(this)
            builder.setMessage(R.string.exit)
                .setPositiveButton(
                    R.string.yes

                ) { dialog, id ->
                    super.onBackPressed()
                }
                .setNegativeButton(
                    R.string.no
                ) { dialog, id ->

                }
            // Create the AlertDialog object and return it
            builder.create().show()
        }

    }
}