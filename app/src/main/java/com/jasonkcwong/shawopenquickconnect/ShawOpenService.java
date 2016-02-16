package com.jasonkcwong.shawopenquickconnect;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by jason on 16-02-15.
 */
public class ShawOpenService extends Service{
    private String email;
    private String pass;
    private Boolean isRunning;
    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        isRunning = false;
        //Toast.makeText(this,"Service is created" ,Toast.LENGTH_LONG).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //Started
        email = intent.getStringExtra("email");
        pass = intent.getStringExtra("pass");

        Toast.makeText(this,email + pass ,Toast.LENGTH_LONG).show();
        //Log.v("Current SSID", getCurrentSsid(this));
        if (!isRunning){
            Executor executor = Executors.newSingleThreadExecutor();
            executor.execute(new Runnable() {
                public void run() {
                    isRunning = true;
                    while(isRunning)
                    {
                        try {
                            Thread.sleep(5000);


                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        //REST OF CODE HERE//
                        Log.v("Timer", "Every five seconds");
                    }
                }
            });
        }

//        new Thread(new Runnable(){
//            public void run() {
//                // TODO Auto-generated method stub
//                while(true)
//                {
//                    try {
//                        Thread.sleep(5000);
//
//
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    //REST OF CODE HERE//
//                    Log.v("Timer", "Every five seconds");
//                }
//
//            }
//        }).start();

        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this,"Service is stopped" ,Toast.LENGTH_LONG).show();
        isRunning = false;
    }
    public static String getCurrentSsid(Context context) {
        String ssid = null;
        ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (networkInfo.isConnected()) {
            final WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            final WifiInfo connectionInfo = wifiManager.getConnectionInfo();
            if (connectionInfo != null && !TextUtils.isEmpty(connectionInfo.getSSID())) {
                ssid = connectionInfo.getSSID();
            }
        }
        return ssid;
    }
}
