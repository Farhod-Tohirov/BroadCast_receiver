package com.star.broadcastreceiverlesson14

import android.content.Context
import android.content.SharedPreferences

class LocalStorage private constructor(context: Context) {
    companion object {
        lateinit var instance: LocalStorage; private set

        fun init(context: Context) {
            instance = LocalStorage(context)
        }
    }

    private val pref: SharedPreferences =
        context.getSharedPreferences("LocalStorage", Context.MODE_PRIVATE)

    var pilotOn by BooleanPreference(pref, true)
    var wifiOn by BooleanPreference(pref, true)
    var bluetoothOn by BooleanPreference(pref, true)
    var chargeOn by BooleanPreference(pref, true)
}