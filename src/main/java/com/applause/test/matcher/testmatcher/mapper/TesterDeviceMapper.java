package com.applause.test.matcher.testmatcher.mapper;

import javax.persistence.*;

@Entity
@Table(name = "tester_device")
public class TesterDeviceMapper {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "tester_id")
  private Long testerId;

  @Column(name = "device_id")
  private Long deviceId;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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
