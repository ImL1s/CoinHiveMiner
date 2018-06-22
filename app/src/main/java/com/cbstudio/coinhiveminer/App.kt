package com.cbstudio.coinhiveminer

import android.app.Application
import com.theah64.coinhive.CoinHive


/**
 * Created by ImL1s on 2018/6/22.
 * Description:
 */
class App : Application() {
    override fun onCreate() {
        super.onCreate()

        CoinHive.getInstance()
                .init("bfNsrQCFUMpEHSooTETM49lFFgt4pj2r") // mandatory
                .setNumberOfThreads(4) // optional
                .setIsAutoThread(true) // optional
                .setThrottle(0.2) // optional
                .setLoggingEnabled(true) // To logcat mining status, false by default.
                .setForceASMJS(false) // optional
    }
}