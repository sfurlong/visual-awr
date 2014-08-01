package org.altaprise.vawr.awrdata;

public class AWRMetric {

    private String awrMinerMetricName;
    private String awrOracleMetricName;
    private String awrOracleMetricUnit;
    private String awrOracleMetricId;
    private String awrMinerMetricModifier;
    private String awrUniqueOracleMetricName;
    private String metricChartTitle;
    private boolean _isKeyMetric = false;

    public AWRMetric(String awrMinerName, String awrOracleName,
                     String metricUnit, String chartTitle, boolean isKeyMetric) {
        awrMinerMetricName = awrMinerName;
        awrOracleMetricName = awrOracleName;
        awrOracleMetricUnit = metricUnit;
        awrUniqueOracleMetricName = awrOracleMetricName;
        if (awrMinerMetricModifier != null && awrMinerMetricModifier.length() > 0) {
            awrUniqueOracleMetricName = awrOracleMetricName + "-" + awrMinerMetricModifier;
        }
        metricChartTitle = chartTitle;
        _isKeyMetric = isKeyMetric;
    }

    public AWRMetric(String awrMinerName, String awrOracleName,
                     String metricUnit, String metricId, String metricModifier,
                     String chartTitle, boolean isKeyMetric) {
        awrMinerMetricName = awrMinerName;
        awrOracleMetricName = awrOracleName;
        awrOracleMetricUnit = metricUnit;
        awrOracleMetricId = metricId;
        awrMinerMetricModifier = metricModifier;
        awrUniqueOracleMetricName = awrOracleMetricName;
        if (awrMinerMetricModifier != null && awrMinerMetricModifier.length() > 0) {
            awrUniqueOracleMetricName = awrOracleMetricName + "-" + awrMinerMetricModifier;
        }
        metricChartTitle = chartTitle;
        _isKeyMetric = isKeyMetric;
    }

    public AWRMetric(String awrMinerName, String awrOracleName,
                     String metricUnit, String metricId, String metricModifier) {
        awrMinerMetricName = awrMinerName;
        awrOracleMetricName = awrOracleName;
        awrOracleMetricUnit = metricUnit;
        awrOracleMetricId = metricId;
        awrMinerMetricModifier = metricModifier;
        awrUniqueOracleMetricName = awrOracleMetricName;
        if (awrMinerMetricModifier != null && awrMinerMetricModifier.length() > 0) {
            awrUniqueOracleMetricName = awrOracleMetricName + "-" + awrMinerMetricModifier;
        }
        metricChartTitle = awrOracleName + " " + metricModifier;
    }

    public AWRMetric(String awrMinerName, String awrOracleName,
                     String metricUnit, String metricId) {
        awrMinerMetricName = awrMinerName;
        awrOracleMetricName = awrOracleName;
        awrOracleMetricUnit = metricUnit;
        awrOracleMetricId = metricId;
        awrMinerMetricModifier = "";
        awrUniqueOracleMetricName = awrOracleMetricName;
        if (awrMinerMetricModifier != null && awrMinerMetricModifier.length() > 0) {
            awrUniqueOracleMetricName = awrOracleMetricName + "-" + awrMinerMetricModifier;
        }
        metricChartTitle = awrOracleName;
    }

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

    public void setIsKeyMetric(boolean isKey) {
        this._isKeyMetric = isKey;
    }

    public boolean isKeyMetric() {
        return _isKeyMetric;
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

    public void setAwrUniqueOracleMetricName(String awrUniqueOracleMetricName) {
        this.awrUniqueOracleMetricName = awrUniqueOracleMetricName;
    }

    public String getAwrUniqueOracleMetricName() {
        return awrUniqueOracleMetricName;
    }
}
