package com.example.backposturecorrector.statistics;

public class WeeklyStatisticsResponse {

    private String dayOfWeek;
    private int numberOfAlerts;

    public WeeklyStatisticsResponse(String dayOfWeek, int numberOfAlerts) {
        this.dayOfWeek = dayOfWeek;
        this.numberOfAlerts = numberOfAlerts;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public int getNumberOfAlerts() {
        return numberOfAlerts;
    }
}
