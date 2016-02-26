package com.jasonkcwong.shawopenquickconnect;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.IBinder;
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
    private Receiver receiver;

    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        isRunning = false;
        receiver = new Receiver();

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(WifiManager.SUPPLICANT_STATE_CHANGED_ACTION);
        this.registerReceiver(receiver, intentFilter);
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

        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this,"Service is stopped" ,Toast.LENGTH_LONG).show();
        isRunning = false;
    }

}
