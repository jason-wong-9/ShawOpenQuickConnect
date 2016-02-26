package com.jasonkcwong.shawopenquickconnect;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by jason on 16-02-16.
 */
public class Receiver extends BroadcastReceiver {
    private String email;
    private String pass;
    @Override
    public void onReceive(Context context, Intent intent) {
        final String action = intent.getAction();
        email = intent.getStringExtra("email");
        pass = intent.getStringExtra("pass");
        Log.v("Trigger", "onReceive");
        if (WifiManager.NETWORK_STATE_CHANGED_ACTION.equals (action)) {
            NetworkInfo netInfo = intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
            if (ConnectivityManager.TYPE_WIFI == netInfo.getType()) {
                boolean connected = checkConnectedToDesiredWifi(context);
                if (connected){
                    Intent i = new Intent(context, ShawOpenService.class);
                    Bundle extras = new Bundle();
                    Log.v("email", email);
                    extras.putString("email", email);
                    extras.putString("pass", pass);
                    i.putExtras(extras);
                    context.startService(i);
                } else {
                    Intent i = new Intent(context, ShawOpenService.class);
                    context.stopService(i);
                }
            }
        }
    }

    private boolean checkConnectedToDesiredWifi(Context context) {
        boolean connected = false;

        String desiredName = "Shaw Open";

        WifiManager wifiManager =
                (WifiManager) context.getSystemService(Context.WIFI_SERVICE);

        WifiInfo wifi = wifiManager.getConnectionInfo();
        if (wifi != null) {
            String ssid = wifi.getSSID();
            Log.v("SSID",ssid);
            connected = desiredName.equals(ssid);
        }

        return connected;
    }
}
