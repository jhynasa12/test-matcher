package com.applause.test.matcher.testmatcher.device;

import com.applause.test.matcher.testmatcher.bug.Bug;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

@Entity
@Table(name = "devices")
public class Mobile extends BaseDevice {

    @Transient
    private List<Bug> bugs;

    public Mobile() {
        super();
    }

    public List<Bug> getBugs() {
        return bugs;
    }

    public void setBugs(List<Bug> bugs) {
        this.bugs = bugs;
    }
}
