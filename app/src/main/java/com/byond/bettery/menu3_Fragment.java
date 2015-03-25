package com.byond.bettery;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by smoshe4 on 3/23/2015.
 */
public class menu3_Fragment extends Fragment {
    View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       

        view = inflater.inflate(R.layout.menu3,container,false);
        return view;
    }
}
