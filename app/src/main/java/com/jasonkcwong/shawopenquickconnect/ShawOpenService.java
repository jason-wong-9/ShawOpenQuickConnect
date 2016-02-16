package com.jasonkcwong.shawopenquickconnect;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by jason on 16-02-15.
 */
public class ShawOpenService extends Service{
    private String email;
    private String pass;
    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        //Toast.makeText(this,"Service is created" ,Toast.LENGTH_LONG).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //Started
        email = intent.getStringExtra("email");
        pass = intent.getStringExtra("pass");

        Toast.makeText(this,email + pass ,Toast.LENGTH_LONG).show();


        new Thread(new Runnable(){
            public void run() {
                // TODO Auto-generated method stub
                while(true)
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
        }).start();

        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this,"Service is stopped" ,Toast.LENGTH_LONG).show();
    }
}
