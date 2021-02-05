package com.example.panicbuttonui

import android.app.Activity
import android.content.Context
import android.os.Build
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide

/**
 * Created by imambudi808 on 05/02/2021.
 */

/*use this ti validate phone number*/
fun isValidPhoneNumber(phoneNumber: String?): Boolean {
    if (phoneNumber.isNullOrEmpty()) return false
    if (!TextUtils.isDigitsOnly(phoneNumber)) return false
    if (phoneNumber.length < 10 || phoneNumber.length > 14) return false
    return true
}

/*use this to validate multiple string variables*/
fun isNullOrEmpty(strings: Array<String?>): Boolean {
    strings.forEach {
        if (it.isNullOrEmpty()) {
            return true
        }
    }
    return false
}

fun Context.toast(message: String?): Toast {
    return Toast.makeText(this, message, Toast.LENGTH_SHORT).apply {
        show()
    }
}

fun Activity.setLightStatusBar(isLight: Boolean = true) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        window.decorView.systemUiVisibility = if (isLight) View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR else 0
    } else {
        window.decorView.systemUiVisibility = 0
    }
}

fun Activity.setStatusBackgroundColor(bgColor: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        window.statusBarColor = bgColor
    }
}

/**
 * Safely get color from resources
 */
fun Context?.getMyColor(@ColorRes id: Int): Int {
    this?.let {
        return ContextCompat.getColor(it, id)
    }
    return 0
}

fun ImageView.loadImage(url: String?) {
    Glide.with(this).load(url).into(this)
}
