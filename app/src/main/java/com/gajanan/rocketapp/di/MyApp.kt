package com.gajanan.rocketapp.di

import android.app.Application
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.FormatStrategy
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class MyRocketApp : Application() {

    override fun onCreate() {
        super.onCreate()

        val formatStrategy : FormatStrategy = PrettyFormatStrategy
            .newBuilder()
            .showThreadInfo( true)
            .methodCount(1)
            .methodOffset(5)
            .build()

        Logger.addLogAdapter(AndroidLogAdapter(formatStrategy))
        Timber.plant(object : Timber.DebugTree(){

            override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
                Logger.log(priority, tag, message, t)
            }
        })

        Timber.d("Inside App!!")
    }

}