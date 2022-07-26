package com.applause.test.matcher.testmatcher.device;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "device_type", discriminatorType = DiscriminatorType.INTEGER)
public class BaseDevice {

  @Id
  @Column(name = "device_id")
  private Long deviceId;

  @Column(name = "description")
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
