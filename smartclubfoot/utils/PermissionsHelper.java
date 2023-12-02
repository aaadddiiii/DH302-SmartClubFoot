package com.ant.smartclubfoot.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class PermissionsHelper {
    private static final String TAG = "PermissionsHelper";

    public static final String WRITE_EXTERNAL_STORAGE = Manifest.permission.WRITE_EXTERNAL_STORAGE;
    public static final String RECORD_AUDIO = Manifest.permission.RECORD_AUDIO;
    public static final String READ_EXTERNAL_STORAGE = Manifest.permission.READ_EXTERNAL_STORAGE;
    public static final String BLUETOOTH_ADMIN = Manifest.permission.BLUETOOTH_ADMIN;
    public static final String ACCESS_FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    public static final String ACCESS_NETWORK_STATE = Manifest.permission.ACCESS_NETWORK_STATE;
    public static final String ACCESS_WIFI_STATE = Manifest.permission.ACCESS_WIFI_STATE;
    public static final String INTERNET = Manifest.permission.INTERNET;
    public static final String WAKE_LOCK = Manifest.permission.WAKE_LOCK;
    public static final String SYSTEM_ALERT_WINDOW = Manifest.permission.SYSTEM_ALERT_WINDOW;
    public static final String READ_PHONE_STATE = Manifest.permission.READ_PHONE_STATE;

    private static final String[] REQUIRED_PERMISSIONS = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.BLUETOOTH_ADMIN,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_NETWORK_STATE,
            Manifest.permission.ACCESS_WIFI_STATE,
            Manifest.permission.INTERNET,
            Manifest.permission.WAKE_LOCK,
            Manifest.permission.SYSTEM_ALERT_WINDOW

    };

    public static final int PERMISSION_ALLOW_WRITE_EXTERNAL_STORAGE = 0;
    public static final int PERMISSION_ALLOW_RECORD_AUDIO = 1;
    public static final int PERMISSION_ALLOW_READ_EXTERNAL_STORAGE = 2;
    public static final int PERMISSION_ALLOW_BLUETOOTH_ADMIN = 3;
    public static final int PERMISSION_ALLOW_ACCESS_FINE_LOCATION = 4;
    public static final int PERMISSION_ALLOW_ACCESS_NETWORK_STATE = 5;
    public static final int PERMISSION_ALLOW_ACCESS_WIFI_STATE = 6;
    public static final int PERMISSION_ALLOW_INTERNET = 7;
    public static final int PERMISSION_ALLOW_WAKE_LOCK = 8;
    public static final int PERMISSION_ALLOW_SYSTEM_ALERT_WINDOW = 9;
    public static final int PERMISSION_ALLOW_READ_PHONE_STATE = 10;

    public static final int ALLOW_ALL = 11;


    private static String[] getRequiredPermissions(final Context context, String[] requiredPermissions) {
        ArrayList<String> pendingPermissions = new ArrayList<>();

        for (String permission : requiredPermissions) {
            if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED)
                pendingPermissions.add(permission);
        }

        return pendingPermissions.toArray(new String[pendingPermissions.size()]);
    }

    public static void askPermissions(final Context context) {

        String[] permissions = getRequiredPermissions(context, REQUIRED_PERMISSIONS);

        if (permissions.length > 0)
            ActivityCompat.requestPermissions((Activity) context, permissions, ALLOW_ALL);
    }

    private static int getIndexFor(String permisiion) {
        for (int i = 0; i < REQUIRED_PERMISSIONS.length; i++) {
            if (permisiion.equals(REQUIRED_PERMISSIONS[i])) {
                return i;
            }
        }

        return -1;
    }

    public static boolean askPermission(final Context context, String permission) {
        if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) context, new String[]{permission}, getIndexFor(permission));
            return false;
        }

        return true;
    }
}
