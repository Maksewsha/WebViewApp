package com.maksewsha.webviewapp

import android.content.Intent
import android.net.Uri
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.core.content.ContextCompat.startActivity


class MyWebViewClient : WebViewClient() {

    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        val str = request!!.url.toString()
        if (str.contains("yandex.ru/maps")) {
            val uri = Uri.parse("yandexmaps://maps.yandex.ru/?${str.substringAfter("maps/")}")
            val intent = view!!.context.packageManager.getLaunchIntentForPackage("ru.yandex.maps")
            addition(intent, view, str)
        } else if (str.contains("yandex.ru/pogoda")){
            val intent = view!!.context.packageManager.getLaunchIntentForPackage("ru.yandex.pogoda")
            addition(intent, view, str)
        }
        return super.shouldOverrideUrlLoading(view, request)
    }

    private fun addition(intent: Intent?, view: View, str: String){
        if(intent != null){
            startActivity(view.context, intent, null)
        } else {
            startActivity(
                view.context,
                Intent(Intent.ACTION_VIEW, Uri.parse(str)), null
            )
        }
    }
}