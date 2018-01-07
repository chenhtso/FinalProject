package com.example.koishi.finalproject;

import android.app.Service;
import android.app.admin.DevicePolicyManager;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.Calendar;

/**
 * Created by Koishi on 1/7/2018.
 */

public class MonitorService extends Service {
    private int limitedHour;

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
    }

    private void lockScreen() {
        DevicePolicyManager devicePolicyManager = (DevicePolicyManager) getApplicationContext().getSystemService(Context.DEVICE_POLICY_SERVICE);
        devicePolicyManager.lockNow();
    }

    private void sendSMS() {

    }

    private class MyThread extends Thread {
        @Override
        public void run() {
            readSettingAndSet();
            int currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);

            if (currentHour >= limitedHour) {
                lockScreen();
            }

            if (currentHour == 22) {
                sendSMS();
            }
            super.run();
        }
    }

}
