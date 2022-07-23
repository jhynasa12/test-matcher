package com.applause.test.matcher.testmatcher.devices;

public abstract class BaseDevice {

    private Long deviceId;
    private String description;


    public Long getDeviceId() {
        return deviceId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
