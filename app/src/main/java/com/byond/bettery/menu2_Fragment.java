package com.byond.bettery;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ToggleButton;

/**
 * Created by smoshe4 on 3/23/2015.
 */
public class menu2_Fragment extends Fragment  {
    View view;
    private TextView txtBattery;
    public String mTAG = "menu2_Fragment";
    public int mBtrySts = 0;
    public String mCrgrSts = "";
    public boolean mTglBtn = true;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.menu2,container,false);
        registerForContextMenu(view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().getApplicationContext();
        BroadcastReceiver batteryReceiver = new BroadcastReceiver() {
            int scale = -1;
            int level = -1;
            int voltage = -1;
            int temp = -1;
            @Override
            public void onReceive(Context context, Intent intent) {
                level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
                scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
                temp = intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, -1);
                voltage = intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, -1);
                mBtrySts = level;
                setText();
            }
        };

        BroadcastReceiver chargerReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                String action = intent.getAction();

                if(action.equals(Intent.ACTION_POWER_CONNECTED)) {
                    Log.d(mTAG, "ACTION_POWER_CONNECTED");
                    mCrgrSts = "Charging...";
                    setText();
                    // Do something when power connected

                }
                else if(action.equals(Intent.ACTION_POWER_DISCONNECTED)) {
                    Log.d(mTAG,"ACTION_POWER_DISCONNECTED");
                    mCrgrSts = "";
                    setText();
                    // Do something when power disconnected
                }

            }
        };

        IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        getActivity().registerReceiver(batteryReceiver, filter);


        IntentFilter intentPowerDisconnectedOnFilter = new IntentFilter();
        intentPowerDisconnectedOnFilter.addAction("android.intent.action.ACTION_POWER_DISCONNECTED");
        intentPowerDisconnectedOnFilter.addAction("android.intent.action.ACTION_POWER_CONNECTED");
        getActivity().registerReceiver(chargerReceiver, intentPowerDisconnectedOnFilter);

        ToggleButton toggle = (ToggleButton) view.findViewById(R.id.toggleButton2);
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mTglBtn = true;
                    setText();
                } else {
                    mTglBtn = false;
                    setText();
                }
            }
        });
    }

    public void setText(){
        TextView txtBattery = (TextView)view.findViewById(R.id.myTextProgress);
        if (mTglBtn) {
            txtBattery.setText(mBtrySts + "% " + mCrgrSts);
        }
        else
        {
            txtBattery.setText(mBtrySts + "% Charger Disabled");
        }
        ProgressBar progressBar = (ProgressBar)view.findViewById((R.id.progressBar2));
        progressBar.setProgress(mBtrySts);
    }


}
