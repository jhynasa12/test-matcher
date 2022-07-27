package com.applause.test.matcher.testmatcher.device;

public class MobileDto {

  private String deviceId;
  private String description;
  private Integer numberOfBugsByTester;

  public MobileDto() {}

  public MobileDto(String deviceId, String description, Integer numberOfBugsByTester) {
    this.deviceId = deviceId;
    this.description = description;
    this.numberOfBugsByTester = numberOfBugsByTester;
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

  public Integer getNumberOfBugsByTester() {
    return numberOfBugsByTester;
  }

  public void setNumberOfBugsByTester(Integer numberOfBugsByTester) {
    this.numberOfBugsByTester = numberOfBugsByTester;
  }
}
