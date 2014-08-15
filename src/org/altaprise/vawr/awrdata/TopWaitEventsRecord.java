package org.altaprise.vawr.awrdata;

public class TopWaitEventsRecord {
    private String waitClass = "";
    private String eventName = "";
    private double cummulativeWaitTime = 0.0;
    private double cummDBWaitTimePct = 0.0;

    public void setCummDBWaitTimePct(double cummDBWaitTimePct) {
        this.cummDBWaitTimePct = cummDBWaitTimePct;
    }

    public double getCummDBWaitTimePct() {
        return cummDBWaitTimePct;
    }
    private static double cummTotalAllWaitEventsTimeSec = 0.0;

    public static void setCummTotalAllWaitEventsTimeSec(double cummTotalAllWaitEventsTimeSec) {
        TopWaitEventsRecord.cummTotalAllWaitEventsTimeSec = cummTotalAllWaitEventsTimeSec;
    }

    public static double getCummTotalAllWaitEventsTimeSec() {
        return cummTotalAllWaitEventsTimeSec;
    }

    public void setWaitClass(String waitClass) {
        this.waitClass = waitClass;
    }

    public String getWaitClass() {
        return waitClass;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventName() {
        return eventName;
    }

    public void setCummulativeWaitTime(double cummulativeWaitTime) {
        this.cummulativeWaitTime = cummulativeWaitTime;
    }

    public double getCummulativeWaitTime() {
        return cummulativeWaitTime;
    }
}
