package com.applause.test.matcher.testmatcher.device;

import com.applause.test.matcher.testmatcher.tester.Tester;

import javax.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue("1")
public class Mobile extends BaseDevice {

  @ManyToMany(mappedBy = "mobileDevices", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  private List<Tester> testers;

  public Mobile() {
    super();
  }

  public List<Tester> getTesters() {
    return testers;
  }

  public void setTesters(List<Tester> testers) {
    this.testers = testers;
  }
}
