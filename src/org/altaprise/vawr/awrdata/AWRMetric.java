package org.altaprise.vawr.awrdata;

public class AWRMetric {

    public AWRMetric(String awrMinerName, String awrOracleName,
                     String metricUnit, String chartTitle, boolean chartable) {
        awrMinerMetricName = awrMinerName;
        awrOracleMetricName = awrOracleName;
        awrOracleMetricUnit = metricUnit;
        metricChartTitle = chartTitle;
        isChartable = chartable;
    }

    public AWRMetric(String awrMinerName, String awrOracleName,
                     String metricUnit, String metricId, String metricModifier,
                     String chartTitle, boolean chartable) {
        awrMinerMetricName = awrMinerName;
        awrOracleMetricName = awrOracleName;
        awrOracleMetricUnit = metricUnit;
        awrOracleMetricId = metricId;
        awrMinerMetricModifier = metricModifier;
        metricChartTitle = chartTitle;
        isChartable = chartable;
    }

    public AWRMetric(String awrMinerName, String awrOracleName,
                     String metricUnit, String metricId, String metricModifier) {
        awrMinerMetricName = awrMinerName;
        awrOracleMetricName = awrOracleName;
        awrOracleMetricUnit = metricUnit;
        awrOracleMetricId = metricId;
        awrMinerMetricModifier = metricModifier;
        metricChartTitle = awrOracleName + " " + metricModifier;
        isChartable = true;
    }

    public AWRMetric(String awrMinerName, String awrOracleName,
                     String metricUnit, String metricId) {
        awrMinerMetricName = awrMinerName;
        awrOracleMetricName = awrOracleName;
        awrOracleMetricUnit = metricUnit;
        awrOracleMetricId = metricId;
        awrMinerMetricModifier = "";
        metricChartTitle = awrOracleName;
        isChartable = true;
    }

    private String awrMinerMetricName;
    private String awrOracleMetricName;
    private String awrOracleMetricUnit;
    private String awrOracleMetricId;
    private String awrMinerMetricModifier;
    private String metricChartTitle;
    private boolean isChartable = false;

    public void setAWRMinerMetricName(String metricName) {
        this.awrMinerMetricName = metricName;
    }

    public String getAWRMinerMetricName() {
        return awrMinerMetricName;
    }

    public void setAWROracleMetricName(String metricDescription) {
        this.awrOracleMetricName = metricDescription;
    }

    public String getAWROracleMetricName() {
        return awrOracleMetricName;
    }

    public void setAWROracleMetricUnit(String metricChartRangeDescription) {
        this.awrOracleMetricUnit = metricChartRangeDescription;
    }

    public String getAWROracleMetricUnit() {
        return awrOracleMetricUnit;
    }

    public void setMetricChartTitle(String metricChartTitle) {
        this.metricChartTitle = metricChartTitle;
    }

    public String getMetricChartTitle() {
        return metricChartTitle;
    }

    public void setIsChartable(boolean isChartable) {
        this.isChartable = isChartable;
    }

    public boolean isIsChartable() {
        return isChartable;
    }

    public void setAwrOracleMetricId(String awrOracleMetricId) {
        this.awrOracleMetricId = awrOracleMetricId;
    }

    public String getAwrOracleMetricId() {
        return awrOracleMetricId;
    }

    public void setAwrMinerMetricModifier(String awrMinerMetricModifier) {
        this.awrMinerMetricModifier = awrMinerMetricModifier;
    }

    public String getAwrMinerMetricModifier() {
        return awrMinerMetricModifier;
    }
}
