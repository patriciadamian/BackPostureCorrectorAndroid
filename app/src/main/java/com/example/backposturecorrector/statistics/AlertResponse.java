package com.example.backposturecorrector.statistics;

import java.time.LocalDateTime;

public class AlertResponse {

    private Long id;
    private LocalDateTime correctionAlertDate;
    private Long userId;

    public AlertResponse(Long id, LocalDateTime correctionAlertDate, Long userId) {
        this.id = id;
        this.correctionAlertDate = correctionAlertDate;
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "AlertResponse{" +
                "id=" + id +
                ", correctionAlertDate=" + correctionAlertDate +
                ", userId=" + userId +
                '}';
    }
}
