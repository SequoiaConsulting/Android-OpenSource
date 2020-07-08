package com.sequoia.android.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.ConsoleMessage
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import com.google.gson.Gson
import com.sequoia.android.javascriptwebviewdemo.R
import com.sequoia.android.model.UserDetails
import com.sequoia.android.utils.DemoJavaScriptInterface
import kotlinx.android.synthetic.main.webview_activity.*

class WebviewActivity : BaseActivity() {

    companion object {
        private val TAG = WebviewActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.webview_activity)
        common_web_view.addJavascriptInterface(
            DemoJavaScriptInterface(
                this
            ), "NativeHandler"
        )
        initWebViewSettingsAndClient("file:///android_asset/index.html")
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebViewSettingsAndClient(url: String) {
        if (isDestroyed || isFinishing) return
        val settings: WebSettings = common_web_view.getSettings()
        settings.javaScriptEnabled = true
        settings.javaScriptCanOpenWindowsAutomatically = true
        common_web_view.setWebChromeClient(object : WebChromeClient() {
            override fun onConsoleMessage(consoleMessage: ConsoleMessage): Boolean {
                Log.d(
                    TAG, consoleMessage.message() + " -- From line "
                            + consoleMessage.lineNumber() + " of "
                            + consoleMessage.sourceId()
                )
                return super.onConsoleMessage(consoleMessage)
            }
        })
        common_web_view.loadUrl(url)
    }

    override fun callApi() {
        val url = "https://github.com"
        loadHtmlInWebView(url)
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun loadHtmlInWebView(url: String) {
        common_web_view.loadUrl(url)
        //common_web_view.evaluateJavascript("setUrlInText('" + url + "')", null)
    }

    fun sendDataToWebview(view: View) {
        val name = etName.text.toString()
        val email = etEmail.text.toString()
        val phone = etPhone.text.toString()
        val userDetails =
            UserDetails(name, email, phone)

        val strUserDetails: String = Gson().toJson(userDetails)
        common_web_view.evaluateJavascript(
            "getData($strUserDetails)", object : ValueCallback<String> {
                override fun onReceiveValue(value: String?) {
                    val userDetails = Gson().fromJson(value, UserDetails::class.java)
                    etName.setText(userDetails.name)
                    etEmail.setText(userDetails.email)
                    etPhone.setText(userDetails.phoneNumber)
                    Log.e(TAG, value)
                }
            })
    }
}