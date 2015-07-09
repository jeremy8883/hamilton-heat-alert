package net.jeremycasey.hamiltonheatalert.heatstatus;

import org.joda.time.DateTime;

public class LastFetchedHeatStatus {
    private int stage;
    private long dateTimeMillis;

    public LastFetchedHeatStatus(HeatStatus heatStatus) {
        this.stage = heatStatus.getStage();
        this.dateTimeMillis = new DateTime().getMillis();
    }

    public LastFetchedHeatStatus(int status, long dateTime) {
        this.stage = status;
        this.dateTimeMillis = dateTime;
    }

    public int getStage() {
        return stage;
    }

    public void setStage(int stage) {
        this.stage = stage;
    }

    public long getDateTimeMillis() {
        return dateTimeMillis;
    }

    public void setDateTimeMillis(long dateTimeMillis) {
        this.dateTimeMillis = dateTimeMillis;
    }
}