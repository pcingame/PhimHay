package com.pcingame.phimhay.common.extension

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.widget.Toast
import androidx.annotation.StringRes
import kotlin.random.Random

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.showToast(@StringRes stringResId: Int) {
    Toast.makeText(this, stringResId, Toast.LENGTH_SHORT).show()
}

fun Context.checkAppInstalledOrNot(packetName: String): Boolean {
    try {
        Build.VERSION_CODES.TIRAMISU
        packageManager.getPackageInfoCompat(packetName, PackageManager.GET_ACTIVITIES)
        return true
    } catch (e: PackageManager.NameNotFoundException) {
        e.printStackTrace()
    }
    return false
}

fun PackageManager.getPackageInfoCompat(packageName: String, flags: Int = 0): PackageInfo {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getPackageInfo(packageName, PackageManager.PackageInfoFlags.of(flags.toLong()))
    } else {
        @Suppress("DEPRECATION") getPackageInfo(packageName, flags)
    }
}

fun Context.openPlayStore(playStoreLink: String) {
    try {
        Intent(Intent.ACTION_VIEW).run {
            data = Uri.parse(playStoreLink)
            startActivity(this)
        }
    } catch (e: java.lang.Exception) {
        e.printStackTrace()
    }
}

fun Context.openApp(packetName: String) {
    try {
        packageManager.getLaunchIntentForPackage(packetName)?.apply {
            addCategory(Intent.CATEGORY_LAUNCHER)
            startActivity(this)
        }
    } catch (e: PackageManager.NameNotFoundException) {
        e.printStackTrace()
    }
}

fun Context.openBrowser(url: String) {
    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    startActivity(browserIntent)
}

fun Context.showToast(throwable: Throwable) {
    throwable.message?.let(::showToast)
}

val Context.statusBarHeight: Int
    @SuppressLint("InternalInsetResource", "DiscouragedApi")
    get() {
        val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
        return if (resourceId > 0) resources.getDimensionPixelSize(resourceId) else 0
    }

val Context.navigationBarHeight: Int
    @SuppressLint("InternalInsetResource", "DiscouragedApi")
    get() {
        val resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android")
        return if (resourceId > 0) resources.getDimensionPixelSize(resourceId) else 0
    }

fun Context.getRandomColor(): Int {
    return Color.argb(255, Random.nextInt(256), Random.nextInt(256), Random.nextInt(256))
}
