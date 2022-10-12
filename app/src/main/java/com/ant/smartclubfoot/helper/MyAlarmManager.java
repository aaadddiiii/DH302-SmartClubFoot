package com.ant.smartclubfoot.helper;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.ant.smartclubfoot.activity.receiver.MyAlarmReceiver;

import java.util.Calendar;

public class MyAlarmManager {

    PendingIntent myPendingIntent;
    AlarmManager alarmManager;
    Calendar firingCal;

    public boolean setAlarmManager(Context context, boolean interval) {
        //Register AlarmManager Broadcast receive.x
        firingCal = Calendar.getInstance();
        long intendedTime = 0;
        if(!interval) {
            firingCal.set(Calendar.HOUR_OF_DAY, 16); // At the hour you want to fire the alarm
            firingCal.set(Calendar.MINUTE, 49); // alarm minute
            firingCal.set(Calendar.SECOND, 0); // and alarm second
            intendedTime = firingCal.getTimeInMillis();
        }else {
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
            long currentMilli = firingCal.getTimeInMillis();
            //if (currentMilli >= startHourMilli && currentMilli <= endHourMilli)
                intendedTime = currentMilli + (1 * 60000);
        }
        if(intendedTime != 0) {
            myPendingIntent = PendingIntent.getBroadcast(context, 0, new Intent(context.getApplicationContext(), MyAlarmReceiver.class), PendingIntent.FLAG_UPDATE_CURRENT);
            alarmManager = (AlarmManager) (context.getSystemService(Context.ALARM_SERVICE));
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, intendedTime, AlarmManager.INTERVAL_DAY, myPendingIntent);
            return true;
        }else
            return false;
    }

    public void unregisterAlarmBroadcast(Context context) {
        // todo Unregister onDestroy()
        alarmManager.cancel(myPendingIntent);
    }
}
