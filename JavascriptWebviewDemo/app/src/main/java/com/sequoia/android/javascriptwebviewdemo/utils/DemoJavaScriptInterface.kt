package com.sequoia.android.utils

import android.util.Log
import android.webkit.JavascriptInterface
import com.sequoia.android.activity.BaseActivity

/*
 * Copyright 2019 Sequoia Consulting Group
 * License details with company address
 * 1850 Gateway Dr, Ste 700
 * San Mateo, CA 94404
 *(650) 369.0200
 */

/**
 * Created by Ankita Bajaj on 05/07/20.
 */
class DemoJavaScriptInterface(val activity: BaseActivity) {

    companion object {
        private val TAG = DemoJavaScriptInterface::class.java.simpleName
    }

    @JavascriptInterface
    fun callApi() {
        Log.v(TAG, "call to native")
        activity.runOnUiThread({ activity.callApi() })
    }
}