package com.geekdroid.myapplication

import android.content.*
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.geekdroid.myapplication.utils.MyReceiver


class MainActivity : AppCompatActivity() {
    private lateinit var mNetworkReceiver: BroadcastReceiver
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mNetworkReceiver = MyReceiver()
    }

    private fun registerBroadCast() {
        registerReceiver(mNetworkReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
    }

    //Register broadcast after activity start
    override fun onStart() {
        registerBroadCast()
        super.onStart()
    }

    // don't forget to unregister broadcast
    override fun onStop() {
        super.onStop()
        unregisterReceiver(mNetworkReceiver)
    }

    fun dialog(value: Boolean, context: Context) {
        val dialog = AlertDialog.Builder(context)
        dialog.setTitle("No Internet")
        dialog.setMessage("Check Network Status")
        dialog.setCancelable(false)
        dialog.setPositiveButton("Try Again", DialogInterface.OnClickListener { dialog, which ->
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        })
        val alertDialog: AlertDialog = dialog.create()
        if (!value) {
            alertDialog.show()
        }
    }
}