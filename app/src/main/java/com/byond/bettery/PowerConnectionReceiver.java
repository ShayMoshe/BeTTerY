package com.byond.bettery;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.util.Log;

/**
 * Created by smoshe4 on 3/25/2015.
 */
public class PowerConnectionReceiver extends BroadcastReceiver {

    private  String mTAG = "BeTTerY";

    @Override
    public void onReceive(Context context, Intent intent) {
        int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
        boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
                status == BatteryManager.BATTERY_STATUS_FULL;
        Log.d(mTAG, "Status: "+status);
        Log.d(mTAG, "isCharging: "+ isCharging);

        String action = intent.getAction();

        if(action.equals(Intent.ACTION_POWER_CONNECTED)) {
            Log.d(mTAG, "ACTION_POWER_CONNECTED");
            // Do something when power connected
        }
        else if(action.equals(Intent.ACTION_POWER_DISCONNECTED)) {
            Log.d(mTAG,"ACTION_POWER_DISCONNECTED");
            // Do something when power disconnected
        }
    }
}
