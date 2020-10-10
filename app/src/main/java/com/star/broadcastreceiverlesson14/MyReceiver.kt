package com.star.broadcastreceiverlesson14

import android.bluetooth.BluetoothAdapter
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.wifi.WifiManager

class MyReceiver : BroadcastReceiver() {

    private var chargeOn: ((Boolean) -> Unit)? = null
    private var bluetoothOn: ((Boolean) -> Unit)? = null
    private var wifiOn: ((Boolean) -> Unit)? = null
    private var pilotOn: ((Boolean) -> Unit)? = null


    override fun onReceive(context: Context?, intent: Intent) {
        val action = intent.action

        if (action == WifiManager.WIFI_STATE_CHANGED_ACTION) {
            when (intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, 0)) {
                WifiManager.WIFI_STATE_ENABLED -> wifiOn?.invoke(true)
                WifiManager.WIFI_STATE_DISABLED -> wifiOn?.invoke(false)
            }
        }

        if (action == BluetoothAdapter.ACTION_STATE_CHANGED) {
            when (intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR)) {
                BluetoothAdapter.STATE_ON -> bluetoothOn?.invoke(true)
                BluetoothAdapter.STATE_OFF -> bluetoothOn?.invoke(false)
            }
        }

        if (action == Intent.ACTION_POWER_CONNECTED) chargeOn?.invoke(true)
        if (action == Intent.ACTION_POWER_DISCONNECTED) chargeOn?.invoke(false)
        if (action == Intent.ACTION_AIRPLANE_MODE_CHANGED) {
            val state = intent.getBooleanExtra("state", false)
            pilotOn?.invoke(state)
        }
    }

    fun setOnWifiChangeListener(block: (Boolean) -> Unit) {
        wifiOn = block
    }

    fun setOnBluetoothChangeListener(block: (Boolean) -> Unit) {
        bluetoothOn = block
    }

    fun setOnChargeChangeListener(block: (Boolean) -> Unit) {
        chargeOn = block
    }

    fun setOnPilotChangeListener(block: (Boolean) -> Unit) {
        pilotOn = block
    }
}