package com.example.dayout.config;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import static com.example.dayout.config.AppConstants.ACC_TOKEN;
import static com.example.dayout.config.AppConstants.USER_ID;

public class AppSharedPreferences {

    private static SharedPreferences sp;
    private static SharedPreferences.Editor spEdit;


    @SuppressLint({"applyPrefEdits", "CommitPrefEdits"})
    public static void InitSharedPreferences(Context context) {
        if (sp == null) {
            sp = PreferenceManager.getDefaultSharedPreferences(context);
            spEdit = sp.edit();
        }
    }


    public static void CACHE_AUTH_DATA(int userId,String accToken){
        spEdit.putString(ACC_TOKEN,accToken).putInt(USER_ID,userId).apply();
    }

    public static String GET_ACC_TOKEN(){
        return sp.getString(ACC_TOKEN,"");
    }

    public static int GET_USER_ID(){
        return sp.getInt(USER_ID,0);
    }


}
