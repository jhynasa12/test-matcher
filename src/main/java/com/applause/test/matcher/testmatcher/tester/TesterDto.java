package com.applause.test.matcher.testmatcher.tester;

import com.applause.test.matcher.testmatcher.bug.BugDto;
import com.applause.test.matcher.testmatcher.device.MobileDto;

import java.util.List;

public class TesterDto {

  private String testerId;
  private String firstName;
  private String lastName;
  private String country;
  private String lastLogin;
  private List<MobileDto> mobileDevices;
  private List<BugDto> bugs;

  public TesterDto(
      String testerId,
      String firstName,
      String lastName,
      String country,
      String lastLogin,
      List<MobileDto> mobileDevices,
      List<BugDto> bugs) {
    this.testerId = testerId;
    this.firstName = firstName;
    this.lastName = lastName;
    this.country = country;
    this.lastLogin = lastLogin;
    this.mobileDevices = mobileDevices;
    this.bugs = bugs;
  }

  public String getTesterId() {
    return testerId;
  }

  public void setTesterId(String testerId) {
    this.testerId = testerId;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getLastLogin() {
    return lastLogin;
  }

  public void setLastLogin(String lastLogin) {
    this.lastLogin = lastLogin;
  }

  public List<MobileDto> getMobileDevices() {
    return mobileDevices;
  }

  public void setMobileDevices(List<MobileDto> mobileDevices) {
    this.mobileDevices = mobileDevices;
  }

  public List<BugDto> getBugs() {
    return bugs;
  }

  public void setBugs(List<BugDto> bugs) {
    this.bugs = bugs;
  }
}
