package com.star.broadcastreceiverlesson14

import android.app.Application

class App : Application(){
    override fun onCreate() {
        super.onCreate()
        instance = this
        LocalStorage.init(this)
    }

    companion object{
        lateinit var instance : App
    }
}