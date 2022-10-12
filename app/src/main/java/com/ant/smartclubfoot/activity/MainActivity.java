package com.ant.smartclubfoot.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.ant.smartclubfoot.constants.Constants;

public class MainActivity extends BaseActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences = getSharedPreferences(Constants.SharedPrefConstants.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        /*if(!sharedPreferences.getBoolean(Constants.SharedPrefConstants.IS_LOGGED_IN, false))
            startActivity(new Intent(this,LoginActivity.class));
        else if(sharedPreferences.getString(Constants.SharedPrefConstants.BLE_ADDRESS,"").isEmpty())
            startActivity(new Intent(this,ConnectDeviceActivity.class));
        else
            startActivity(new Intent(this,HomeActivity.class));*/

        if(!sharedPreferences.getBoolean(Constants.SharedPrefConstants.IS_LOGGED_IN, false))
            startActivity(new Intent(this,LoginActivity.class));
        else
            startActivity(new Intent(this,ConnectDeviceActivity.class));
    }
}
