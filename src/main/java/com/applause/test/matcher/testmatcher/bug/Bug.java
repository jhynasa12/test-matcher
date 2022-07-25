package com.applause.test.matcher.testmatcher.bug;

import com.applause.test.matcher.testmatcher.tester.Tester;

import javax.persistence.*;

@Entity
@Table(name = "bugs")
public class Bug {

    @Id
    @Column(name = "bug_id")
    private Long bugId;
    @Column(name = "device_id")
    private Long deviceId;
    @Column(name ="fk_tester_id")
    private Long testerId;

    @ManyToOne
    @JoinColumn(name = "fk_tester_id", nullable = false, insertable = false, updatable = false)
    private Tester tester;

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

    public Tester getTester() {
        return tester;
    }

    public void setTester(Tester tester) {
        this.tester = tester;
    }
}
