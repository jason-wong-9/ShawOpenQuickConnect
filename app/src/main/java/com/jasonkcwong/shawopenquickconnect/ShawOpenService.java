package com.jasonkcwong.shawopenquickconnect;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
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

        Toast.makeText(this,"Service is created" ,Toast.LENGTH_LONG).show();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //Started
        email = intent.getStringExtra("email");
        pass = intent.getStringExtra("pass");
        Toast.makeText(this,email + pass ,Toast.LENGTH_LONG).show();
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this,"Service is stopped" ,Toast.LENGTH_LONG).show();
    }
}
