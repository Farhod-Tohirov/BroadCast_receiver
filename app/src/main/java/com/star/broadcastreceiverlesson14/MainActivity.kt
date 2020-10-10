package com.star.broadcastreceiverlesson14

import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.content.Intent
import android.content.IntentFilter
import android.media.MediaPlayer
import android.net.wifi.WifiManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    @SuppressLint("ClickableViewAccessibility")

    private val myReceiver = MyReceiver()
    private val intentFilter = IntentFilter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        intentFilter.addAction(Intent.ACTION_POWER_CONNECTED)
        intentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED)
        intentFilter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED)
        intentFilter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION)
        intentFilter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)

        registerReceiver(myReceiver, intentFilter)
        loadViews()

    }

    private fun loadViews() {
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { finish() }

        chargeOn.isChecked = LocalStorage.instance.chargeOn
        bluetoothOn.isChecked = LocalStorage.instance.bluetoothOn
        pilotOn.isChecked = LocalStorage.instance.pilotOn
        wifiOn.isChecked = LocalStorage.instance.wifiOn


        chargeOn.setOnLongClickListener {
            chargeOn.setCompoundDrawablesWithIntrinsicBounds(
                R.drawable.ic_baseline_pause_24, 0, 0, 0
            )
            val sound = MediaPlayer.create(this, R.raw.charge_on)
            sound.start()
            sound.setOnCompletionListener {
                chargeOn.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.ic_baseline_play_arrow_24,
                    0,
                    0,
                    0
                )
            }
            true
        }

        wifiOn.setOnLongClickListener {
            wifiOn.setCompoundDrawablesWithIntrinsicBounds(
                R.drawable.ic_baseline_pause_24,
                0,
                0,
                0
            )
            val sound = MediaPlayer.create(this, R.raw.wifi_off)
            sound.start()
            sound.setOnCompletionListener {
                wifiOn.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.ic_baseline_play_arrow_24,
                    0,
                    0,
                    0
                )
            }
            true
        }

        pilotOn.setOnLongClickListener {
            pilotOn.setCompoundDrawablesWithIntrinsicBounds(
                R.drawable.ic_baseline_pause_24,
                0,
                0,
                0
            )
            val sound = MediaPlayer.create(this, R.raw.pilot_on)
            sound.start()
            sound.setOnCompletionListener {
                pilotOn.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.ic_baseline_play_arrow_24,
                    0,
                    0,
                    0
                )
            }
            true
        }

        bluetoothOn.setOnLongClickListener {
            bluetoothOn.setCompoundDrawablesWithIntrinsicBounds(
                R.drawable.ic_baseline_pause_24,
                0,
                0,
                0
            )
            val sound = MediaPlayer.create(this, R.raw.bluetooth_on)
            sound.start()
            sound.setOnCompletionListener {
                bluetoothOn.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.ic_baseline_play_arrow_24,
                    0,
                    0,
                    0
                )
            }
            true
        }

        pilotOn.setOnCheckedChangeListener { _, boolean ->
            LocalStorage.instance.pilotOn = boolean
            Log.d("T12T", boolean.toString())
        }

        wifiOn.setOnCheckedChangeListener { _, boolean ->
            LocalStorage.instance.wifiOn = boolean
            Log.d("T12T", boolean.toString())
        }

        bluetoothOn.setOnCheckedChangeListener { _, boolean ->
            LocalStorage.instance.bluetoothOn = boolean
            Log.d("T12T", boolean.toString())
        }

        chargeOn.setOnCheckedChangeListener { _, boolean ->
            LocalStorage.instance.chargeOn = boolean
            Log.d("T12T", boolean.toString())
        }

        myReceiver.setOnBluetoothChangeListener {
            if (LocalStorage.instance.bluetoothOn) {
                val sound = if (it) MediaPlayer.create(this, R.raw.bluetooth_on)
                else MediaPlayer.create(this, R.raw.bluetooth_off)
                sound.start()
            }
        }
        myReceiver.setOnChargeChangeListener {
            if (LocalStorage.instance.chargeOn) {
                val sound = if (it) MediaPlayer.create(this, R.raw.charge_on)
                else MediaPlayer.create(this, R.raw.charge_off)
                sound.start()
            }
        }
        myReceiver.setOnWifiChangeListener {
            if (LocalStorage.instance.wifiOn) {
                val sound = if (it) MediaPlayer.create(this, R.raw.wifi_on)
                else MediaPlayer.create(this, R.raw.wifi_off)
                sound.start()
            }
        }
        myReceiver.setOnPilotChangeListener {
            if (LocalStorage.instance.pilotOn) {
                val sound = if (it) MediaPlayer.create(this, R.raw.pilot_on)
                else MediaPlayer.create(this, R.raw.pilot_off)
                sound.start()
            }
        }
    }
}