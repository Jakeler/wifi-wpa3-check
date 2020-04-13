package jk.wpa3.check

import android.app.Service
import android.content.Context
import android.content.pm.PackageManager
import android.net.wifi.WifiManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.annotation.RequiresApi
import java.io.File

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val wifi = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager

        if (Build.VERSION.SDK_INT >= 30) {
            log("Band 6 GHz", wifi.is6GHzBandSupported)
            log("STA + AP Concurrent", wifi.isStaApConcurrencySupported)
            log("WAPI", wifi.isWapiSupported)
            log("Wifi 6 (802.11ax)", wifi.isWifiStandardSupported(6))
        }
        if (Build.VERSION.SDK_INT >= 29) {
            log("WPA3-Personal (SAE)", wifi.isWpa3SaeSupported)
            log("WPA3-Enterprise (SuiteB 192)", wifi.isWpa3SuiteBSupported)
            log("WPA3 Enhanced Open (OWE)", wifi.isEnhancedOpenSupported)
            log("WiFi Easy Connect (DPP)", wifi.isEasyConnectSupported)
        }
        if (Build.VERSION.SDK_INT >= 28) {
            log("WiFi RTT (802.11mc)", packageManager.hasSystemFeature(PackageManager.FEATURE_WIFI_RTT))
        }
        if (Build.VERSION.SDK_INT >= 27) {
            log("WiFi Passpoint", packageManager.hasSystemFeature(PackageManager.FEATURE_WIFI_PASSPOINT))
        }
        if (Build.VERSION.SDK_INT >= 26) {
            log("WiFi Aware", packageManager.hasSystemFeature(PackageManager.FEATURE_WIFI_AWARE))
        }
        log("WiFi Direct", packageManager.hasSystemFeature(PackageManager.FEATURE_WIFI_DIRECT))
        log("Band 5 GHz", wifi.is5GHzBandSupported)
        log("P2P", wifi.isP2pSupported)
        log("Offloaded connectivity scan", wifi.isPreferredNetworkOffloadSupported)
        log("Enhanced Power Reporting", wifi.isEnhancedPowerReportingSupported)
        log("Tunnel Directed Link Setup", wifi.isTdlsSupported)
    }

    fun log(name: String, support: Boolean) {
        Log.d(name, if(support) "x" else "-")
    }
}
