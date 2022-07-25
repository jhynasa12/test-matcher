package com.applause.test.matcher.testmatcher.device;

import com.applause.test.matcher.testmatcher.tester.TesterDto;

import java.util.List;

public class MobileDto {

    private String deviceId;
    private String description;
    private List<TesterDto> testers;

    public MobileDto() {
    }

    public MobileDto(String deviceId, String description) {
        this.deviceId = deviceId;
        this.description = description;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<TesterDto> getTesters() {
        return testers;
    }

    public void setTesters(List<TesterDto> testers) {
        this.testers = testers;
    }
}
