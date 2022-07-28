package com.applause.test.matcher.testmatcher.device;

import com.applause.test.matcher.testmatcher.bug.Bug;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "device_type", discriminatorType = DiscriminatorType.INTEGER)
public class BaseDevice {

  @Id
  @Column(name = "device_id")
  private Long deviceId;

  @Column(name = "description")
  private String description;

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "tester", cascade = CascadeType.ALL)
  @Fetch(value = FetchMode.SUBSELECT)
  private List<Bug> bugs;

  @Transient
  private Integer numberOfBugsByTester = 0;

  public Long getDeviceId() {
    return deviceId;
  }

  public void setDeviceId(Long deviceId) {
    this.deviceId = deviceId;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Integer getNumberOfBugsByTester() {
    return numberOfBugsByTester;
  }

  public void setNumberOfBugsByTester(Integer numberOfBugsByTester) {
    this.numberOfBugsByTester = numberOfBugsByTester;
  }

  public List<Bug> getBugs() {
    return bugs;
  }

  public void setBugs(List<Bug> bugs) {
    this.bugs = bugs;
  }
}
