package com.ant.smartclubfoot.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;

public class LocationUtils {
    AlertDialog locationAlertDialog;
    Context context;

    public LocationUtils(Context context) {
        this.context = context;
    }

    public boolean checkIfLocationEnabled() {
        final LocationManager manager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        if (manager != null && !manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps(context);
            return false;
        }
        return true;
    }

    private void buildAlertMessageNoGps(Context context) {
        if (locationAlertDialog == null) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage("Please enable the Location service for searching for device")
                    .setCancelable(false)
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(final DialogInterface dialog, final int id) {
                            context.startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(final DialogInterface dialog, final int id) {
                            dialog.cancel();
                        }
                    });
            locationAlertDialog = builder.create();
        }
        if (!locationAlertDialog.isShowing())
            locationAlertDialog.show();
    }

    public void dismissIfShown(){
        if(locationAlertDialog!=null && locationAlertDialog.isShowing())
            locationAlertDialog.dismiss();
    }
}
