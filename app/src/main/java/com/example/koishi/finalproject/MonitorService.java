package com.example.koishi.finalproject;

import android.app.Service;
import android.app.admin.DevicePolicyManager;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.telephony.SmsManager;

import java.util.Calendar;

/**
 * Created by Koishi on 1/7/2018.
 */

public class MonitorService extends Service {
    private int limitedHour;
    private String phoneNumber;
    private String password;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        new MyThread().run();
        super.onCreate();
    }

    private void readSettingAndSet() {
        limitedHour = 18;
        phoneNumber = "15338094249";
        password = "password";
    }

    private void lockScreen() {
        DevicePolicyManager devicePolicyManager = (DevicePolicyManager) getApplicationContext().getSystemService(Context.DEVICE_POLICY_SERVICE);
        devicePolicyManager.lockNow();
    }

    private void sendSMS() {
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(phoneNumber, null, "Everything is OK", null, null);
    }

    private class MyThread extends Thread {
        @Override
        public void run() {
            readSettingAndSet();
            int currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
            if (currentHour >= limitedHour) {
                lockScreen();
            }
            if (currentHour == 100) {
                sendSMS();
            }
            super.run();
        }
    }

}
