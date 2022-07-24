package com.applause.test.matcher.testmatcher.user;

import com.applause.test.matcher.testmatcher.bug.Bug;

import java.util.List;

public class Tester extends BaseUser{

    private String testerId;
    private List<Bug> bugs;

    public Tester() {
        super();
    }

    public String getTesterId() {
        return testerId;
    }

    public void setTesterId(String testerId) {
        this.testerId = testerId;
    }

    public List<Bug> getBugs() {
        return bugs;
    }

    public void setBugs(List<Bug> bugs) {
        this.bugs = bugs;
    }
}
