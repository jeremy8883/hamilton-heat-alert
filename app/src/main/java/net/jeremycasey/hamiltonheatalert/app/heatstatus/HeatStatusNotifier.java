package net.jeremycasey.hamiltonheatalert.app.heatstatus;

import android.content.Context;

import net.jeremycasey.hamiltonheatalert.app.notifications.HeatStatusNotification;
import net.jeremycasey.hamiltonheatalert.heatstatus.HeatStatus;
import net.jeremycasey.hamiltonheatalert.heatstatus.HeatStatusIsImportantChecker;

public class HeatStatusNotifier {
    private Context mContext;

    public HeatStatusNotifier(Context context) {
        mContext = context;
    }

    public void logAndNotifyIfRequiered(HeatStatus heatStatus) {
        HeatStatusPreferenceLogger logger = new HeatStatusPreferenceLogger(mContext);
        HeatStatus lastNotifiedStatus = logger.getLastNotifiedStatus();
        if (new HeatStatusIsImportantChecker(heatStatus.getStage()).shouldNotify(lastNotifiedStatus)) {
            HeatStatusNotification heatStatusNotification = new HeatStatusNotification(heatStatus, mContext);
            heatStatusNotification.showNotification();
            logger.setLastNotifiedStatus(heatStatus);
        }
        logger.setMostRecentStatus(heatStatus);
    }
}
