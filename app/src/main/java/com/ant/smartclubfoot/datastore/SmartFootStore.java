package com.ant.smartclubfoot.datastore;

import android.content.Context;
import android.content.SharedPreferences;
import com.ant.smartclubfoot.constants.Constants;

import java.util.ArrayList;

public class SmartFootStore {

    public SharedPreferences getSharedPref(Context context){
        return context.getSharedPreferences(Constants.SharedPrefConstants.SHARED_PREF_NAME, Context.MODE_PRIVATE);
    }

    public ArrayList<String> getDataValues(Context context){
        ArrayList<String> data = new ArrayList<>();
        SharedPreferences sharedPreferences = getSharedPref(context);
        for(int i = 1 ; i < 8 ; i++) {
            data.add(sharedPreferences.getString(String.valueOf(i), ""));
        }
        return data;
    }

    public void saveDataValues(Context context,int day,String data){
        SharedPreferences sharedPreferences = getSharedPref(context);
        sharedPreferences.edit().putString(String.valueOf(day),data).apply();
    }

    public void saveTotal(Context context,double total) {
        SharedPreferences sharedPreferences = getSharedPref(context);
        sharedPreferences.edit().putFloat(Constants.SharedPrefConstants.TOTAL_HRS,(float) total).apply();
    }

    public float getTotal(Context context){
        SharedPreferences sharedPreferences = getSharedPref(context);
        return sharedPreferences.getFloat(Constants.SharedPrefConstants.TOTAL_HRS,0);
    }


    public void clear(Context context) {
        getSharedPref(context).edit().clear().apply();
    }
}
