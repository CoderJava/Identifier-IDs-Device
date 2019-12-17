package com.ysn.eresearch

import android.annotation.SuppressLint
import android.content.Context
import android.net.wifi.WifiManager
import android.os.Build
import android.os.Bundle
import android.telephony.TelephonyManager
import android.text.format.Formatter
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initListeners()
    }

    private fun initListeners() {
        button_mac_address_wifi_device_activity_main.setOnClickListener(this)
        button_imei_activity_main.setOnClickListener(this)
        button_simcard_activity_main.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.button_mac_address_wifi_device_activity_main -> {
                getMacAddress()
            }
            R.id.button_imei_activity_main -> {
                getInfoPhoneState()
            }
            R.id.button_simcard_activity_main -> {
                // TODO: do something in here
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun getInfoPhoneState() {
        val telephonyManager = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        var isSupportedMultipleSim = false
        var strImeiSim1 = ""
        var strImeiSim2 = ""
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            isSupportedMultipleSim = true
            strImeiSim1 = telephonyManager.getImei(0)
            strImeiSim2 = telephonyManager.getImei(1)
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            isSupportedMultipleSim = true
            strImeiSim1 = telephonyManager.getDeviceId(0)
            strImeiSim2 = telephonyManager.getDeviceId(1)
        } else {
            strImeiSim1 = telephonyManager.deviceId
        }
        val result = StringBuilder()
        if (isSupportedMultipleSim) {
            result.append("IMEI SIM 1: $strImeiSim1\n")
            result.append("IMEI SIM 2: $strImeiSim2\n")
        } else {
            result.append("IMEI: $strImeiSim1\n")
        }
        text_view_result_activity_main.text = result.toString()
    }

    private fun getMacAddress() {
        val wifiManager =
            getApplicationContext().getSystemService(Context.WIFI_SERVICE) as WifiManager
        val wifiInfo = wifiManager.connectionInfo
        val strMacAddress = wifiInfo.macAddress
        val strIpAddress = Formatter.formatIpAddress(wifiInfo.ipAddress)
        val strBssid = wifiInfo.bssid
        val strFrequency = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            wifiInfo.frequency.toString()
        } else {
            "0"
        }
        val strSsid = wifiInfo.ssid
        val result = StringBuilder()
        result.append("MAC Address: $strMacAddress\n")
            .append("IP Address: $strIpAddress\n")
            .append("BSSID: $strBssid\n")
            .append("Frequency: $strFrequency\n")
            .append("SSID: $strSsid\n")
        text_view_result_activity_main.text = result.toString()
    }
}
