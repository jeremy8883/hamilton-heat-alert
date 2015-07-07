package net.jeremycasey.hamiltonheatalert.app.gcm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;

import net.jeremycasey.hamiltonheatalert.R;
import net.jeremycasey.hamiltonheatalert.app.notifications.HeatStatusNotification;
import net.jeremycasey.hamiltonheatalert.app.notifications.ErrorNotification;
import net.jeremycasey.hamiltonheatalert.heatstatus.HeatStatus;
import net.jeremycasey.hamiltonheatalert.heatstatus.HeatStatusIsImportantChecker;

import com.google.gson.Gson;

//This service is started automatically
public class MyGcmListenerService extends com.google.android.gms.gcm.GcmListenerService {
    private static final String TAG = "MyGcmListenerService";

    public static final String NEW_ALERT_RECEIVED = "newAlertReceived";

    @Override
    public void onMessageReceived(String from, Bundle data) {
        try {
            String heatAdvisoryJson = data.getString("message");

            Gson gson = new Gson();
            HeatStatus heatStatus = gson.fromJson(heatAdvisoryJson, HeatStatus.class);

            HeatStatusNotification heatStatusNotification = new HeatStatusNotification(heatStatus, this);
            if (new HeatStatusIsImportantChecker(heatStatus).isImportant()) {
                heatStatusNotification.showNotification();
            }

            Intent registrationComplete = new Intent(NEW_ALERT_RECEIVED);
            Bundle bundle = new Bundle();
            bundle.putSerializable("heatStatus", heatStatus);
            registrationComplete.putExtras(bundle);
            LocalBroadcastManager.getInstance(this).sendBroadcast(registrationComplete);
        } catch (Exception e) {
            new ErrorNotification(getString(R.string.gcmMessageReceiveError), "", this).show(); //This should never happen
        }

    }
}