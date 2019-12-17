package com.ysn.eresearch

import android.app.admin.DeviceAdminReceiver
import android.app.admin.DevicePolicyManager
import android.content.Context
import android.net.wifi.WifiManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.format.Formatter
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.StringBuilder

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
                // TODO: do something in here
            }
            R.id.button_simcard_activity_main -> {
                // TODO: do something in here
            }
        }
    }

    private fun getMacAddress() {
        val wifiManager = getApplicationContext().getSystemService(Context.WIFI_SERVICE) as WifiManager
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
