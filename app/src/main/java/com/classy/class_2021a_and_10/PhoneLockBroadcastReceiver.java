package com.classy.class_2021a_and_10;

import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telecom.Call;
import android.util.Log;

public class PhoneLockBroadcastReceiver extends BroadcastReceiver {

    public  static int counter = 0;
    public static final String UNLOCK_CHILD = "unlock";
    public static final String UNLOCK_FILENAME = "unlock_log.txt";
    public static final String UNLOCK_ACTION = "android.intent.action.USER_PRESENT";
//
    public interface CallBack_unlocked {
            void unlocked(int val);
    }
//
    private CallBack_unlocked callBack_unlocked;
//
    public PhoneLockBroadcastReceiver(CallBack_unlocked callBack_unlocked) {
        this.callBack_unlocked = callBack_unlocked;
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        KeyguardManager keyguardManager = (KeyguardManager)context.getSystemService(Context.KEYGUARD_SERVICE);
        if (keyguardManager.isKeyguardSecure()) {
            ++counter;
            callBack_unlocked.unlocked(counter);
        }
    }
}
