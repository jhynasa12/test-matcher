package com.applause.test.matcher.testmatcher.device;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "DEVICES")
public class Mobile extends BaseDevice {

    public Mobile() {
        super();
    }
}
