package com.applause.test.matcher.testmatcher.device;

import javax.persistence.*;

@MappedSuperclass
public abstract class BaseDevice {

    @Id
    @Column(name = "DEVICE_ID")
    private Long deviceId;
    @Column(name = "DESCRIPTION")
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
