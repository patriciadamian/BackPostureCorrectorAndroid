package com.example.backposturecorrector.statistics;

public class AnnualStatisticsResponse {

    private String monthOfYear;
    private int numberOfAlerts;

    public AnnualStatisticsResponse(String monthOfYear, int numberOfAlerts) {
        this.monthOfYear = monthOfYear;
        this.numberOfAlerts = numberOfAlerts;
    }

    public String getMonthOfYear() {
        return monthOfYear;
    }

    public int getNumberOfAlerts() {
        return numberOfAlerts;
    }
}
