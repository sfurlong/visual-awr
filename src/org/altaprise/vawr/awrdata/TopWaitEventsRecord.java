/*******************************************************************************
 *
 * Copyright 2015 Stephen Furlong
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
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
