package com.geekdroid.myapplication.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AlertDialog
import com.geekdroid.myapplication.MainActivity

class MyReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        try {
            if (isOnline(context)) {
                MainActivity().dialog(true,context)
                Log.e("androidCenter", "Connected To Internet ")
            } else {
                MainActivity().dialog(false,context)
                Log.e("androidCenter", "Connectivity Failure !!! ")
            }
        } catch (e: NullPointerException) {
            e.printStackTrace()
        }
    }
    @Suppress("DEPRECATION")
    private fun isOnline(context: Context): Boolean {
        return try {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val netInfo = cm.activeNetworkInfo
            //should check null because in airplane mode it will be null
            netInfo != null && netInfo.isConnected
        } catch (e: NullPointerException) {
            e.printStackTrace()
            false
        }
    }
}