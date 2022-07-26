package com.applause.test.matcher.testmatcher.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
public class DataConfig {

  @Value("${devices.location}")
  private String devices;

  @Value("${testers.location}")
  private String testers;

  @Value("${bugs.location}")
  private String bugs;

  @Value("${tester.device.location}")
  private String testerDevice;

  public String getDevices() {
    return devices;
  }

  public String getTesters() {
    return testers;
  }

  public String getBugs() {
    return bugs;
  }

  public String getTesterDevice() {
    return testerDevice;
  }
}
