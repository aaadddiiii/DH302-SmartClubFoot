package com.ant.smartclubfoot.constants;

public class Constants {

    public interface SharedPrefConstants {
        String SHARED_PREF_NAME = "SMARTFOOT";
        String BLE_ADDRESS = "BLEADD";
        String IS_LOGGED_IN = "login";
        String TOTAL_HRS = "total";
        String USERNAME = "username";
        String LAST_ROW = "last_row";
    }

    public interface ACTION {
        String STARTFOREGROUND_ACTION = "start";
        String STARTED_FROM_ALARM = "startFromAlarm";
    }
}
