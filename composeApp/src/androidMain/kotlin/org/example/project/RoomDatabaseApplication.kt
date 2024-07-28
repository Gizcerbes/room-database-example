package org.example.project

import android.app.Application
import android.content.Context

class RoomDatabaseApplication: Application() {


    companion object {

        private var _appContext: Context? = null
        val appContext get() = _appContext!!

    }

    override fun onCreate() {
        _appContext = applicationContext
        super.onCreate()
    }

    override fun onTerminate() {
        _appContext = null
        super.onTerminate()
    }


}