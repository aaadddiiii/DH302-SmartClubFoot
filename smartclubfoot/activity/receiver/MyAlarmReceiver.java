package com.ant.smartclubfoot.activity.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import com.ant.smartclubfoot.constants.Constants;
import com.ant.smartclubfoot.helper.MyAlarmManager;
import com.ant.smartclubfoot.service.UartService;

import java.util.Calendar;

public class MyAlarmReceiver extends BroadcastReceiver {

    public static final String ACTION_ALARM_RECEIVER = "com.ant.smartclubfoot.receiver";

    //This is the call back function(BroadcastReceiver) which will be call when your
    //alarm time will reached.
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("TabrezC", "Receiver Called");
        if(intent.getAction() !=null && intent.getAction().equals("android.intent.action.BOOT_COMPLETED")){
            int start = 20;
            int hours = 6;
            Calendar cal = Calendar.getInstance();
            // set calendar to TODAY 20:00:00.000
            cal.set(Calendar.HOUR_OF_DAY, start);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);

            long startHourMilli = cal.getTimeInMillis();

            // add 14 hours = TOMORROW 02:00:00.000
            cal.add(Calendar.HOUR_OF_DAY, hours);
            long endHourMilli = cal.getTimeInMillis();
            long currentMilli = Calendar.getInstance().getTimeInMillis();
            if (currentMilli >= startHourMilli && currentMilli <= endHourMilli)
                startUARTService(context);
            else {
                MyAlarmManager myAlarmManager = new MyAlarmManager();
                myAlarmManager.setAlarmManager(context,false);
            }

        }else {
            startUARTService(context);
        }
    }

    private void startUARTService(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.SharedPrefConstants.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String myDeviceAddress = sharedPreferences.getString(Constants.SharedPrefConstants.BLE_ADDRESS, "");
        if (!myDeviceAddress.isEmpty()) {
            Log.d("TabrezC","Device Address found starting service");
            Intent startIntent = new Intent(context, UartService.class);
            startIntent.setAction(Constants.ACTION.STARTED_FROM_ALARM);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                context.startForegroundService(startIntent);
            } else {
                context.startService(startIntent);
            }
        }else
            Log.d("TabrezC","Device Address not found");
    }
}
