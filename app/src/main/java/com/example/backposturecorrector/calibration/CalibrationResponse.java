package com.example.backposturecorrector.calibration;

public class CalibrationResponse {

    private Long id;
    private String accelerometerX;
    private String accelerometerY;
    private String accelerometerZ;
    private String gyroscopeX;
    private String gyroscopeY;
    private String gyroscopeZ;
    private String magnetometerX;
    private String magnetometerY;
    private String magnetometerZ;

    public CalibrationResponse(Long id, String accelerometerX, String accelerometerY, String accelerometerZ, String gyroscopeX, String gyroscopeY, String gyroscopeZ, String magnetometerX, String magnetometerY, String magnetometerZ) {
        this.id = id;
        this.accelerometerX = accelerometerX;
        this.accelerometerY = accelerometerY;
        this.accelerometerZ = accelerometerZ;
        this.gyroscopeX = gyroscopeX;
        this.gyroscopeY = gyroscopeY;
        this.gyroscopeZ = gyroscopeZ;
        this.magnetometerX = magnetometerX;
        this.magnetometerY = magnetometerY;
        this.magnetometerZ = magnetometerZ;
    }
}
