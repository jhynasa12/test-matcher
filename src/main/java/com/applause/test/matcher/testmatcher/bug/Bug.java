package com.applause.test.matcher.testmatcher.bug;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bugs_devices_testers")
public class Bug {

    @Id
    @Column(name = "bug_id")
    private Long bugId;
    @Column(name = "device_id")
    private Long deviceId;
    @Column(name ="tester_id")
    private Long testerId;

    public Long getBugId() {
        return bugId;
    }

    public void setBugId(Long bugId) {
        this.bugId = bugId;
    }

    public Long getTesterId() {
        return testerId;
    }

    public void setTesterId(Long testerId) {
        this.testerId = testerId;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }
}
