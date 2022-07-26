package com.applause.test.matcher.testmatcher.utils;

import com.applause.test.matcher.testmatcher.bug.Bug;
import com.applause.test.matcher.testmatcher.bug.BugRepository;
import com.applause.test.matcher.testmatcher.config.DataConfig;
import com.applause.test.matcher.testmatcher.device.DeviceRepository;
import com.applause.test.matcher.testmatcher.device.Mobile;
import com.applause.test.matcher.testmatcher.mapper.TesterDeviceMapper;
import com.applause.test.matcher.testmatcher.mapper.TesterDeviceMapperRepository;
import com.applause.test.matcher.testmatcher.tester.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.stream.Collectors;

@Component
public class DataInitializer {

  Logger logger = LoggerFactory.getLogger(DataInitializer.class);

  private final CsvDataLoader csvDataLoader;
  private final DataConfig dataConfig;
  private final DeviceRepository deviceRepository;
  private final TesterService testerService;
  private final BugRepository bugRepository;
  private final TesterDeviceMapperRepository testerDeviceMapperRepository;

  @Autowired
  public DataInitializer(
      CsvDataLoader csvDataLoader,
      DataConfig dataConfig,
      DeviceRepository deviceRepository,
      TesterService testerService,
      BugRepository bugRepository,
      TesterDeviceMapperRepository testerDeviceMapperRepository) {
    this.csvDataLoader = csvDataLoader;
    this.dataConfig = dataConfig;
    this.deviceRepository = deviceRepository;
    this.testerService = testerService;
    this.bugRepository = bugRepository;
    this.testerDeviceMapperRepository = testerDeviceMapperRepository;
  }

  @PostConstruct
  private void setupData() {
    logger.info("Initializing data");
    deviceRepository.saveAll(csvDataLoader.loadObjectList(Mobile.class, dataConfig.getDevices()));
    testerService.saveAllTesters(
        csvDataLoader.loadObjectList(Tester.class, dataConfig.getTesters()));
    bugRepository.saveAll(csvDataLoader.loadObjectList(Bug.class, dataConfig.getBugs()));
    testerDeviceMapperRepository.saveAll(
        csvDataLoader.loadObjectList(TesterDeviceMapper.class, dataConfig.getTesterDevice()));
    logger.info("initializing data successful");

    Integer numberOfTimesTesterOnDevice = bugRepository.countAllByTesterIdAndDeviceId(4L, 1L);

    System.out.println(
        "Tester userId: "
            + 4
            + " tested this device: "
            + "iPhone 4 "
            + numberOfTimesTesterOnDevice
            + " times.");

    System.out.println(
        "Tester 1: Mobile Devices: "
            + testerService.getTester(1L).getMobileDevices().stream()
                .map(Mobile::getDescription)
                .collect(Collectors.toList()));
    System.out.println(
        "Tester 2: Mobile Devices: "
            + testerService.getTester(2L).getMobileDevices().stream()
                .map(Mobile::getDescription)
                .collect(Collectors.toList()));
    System.out.println(
        "Tester 3: Mobile Devices: "
            + testerService.getTester(3L).getMobileDevices().stream()
                .map(Mobile::getDescription)
                .collect(Collectors.toList()));
    Mobile mobile = (Mobile) deviceRepository.findById(1L).orElse(null);
    Mobile mobile2 = (Mobile) deviceRepository.findById(2L).orElse(null);
    Mobile mobile3 = (Mobile) deviceRepository.findById(3L).orElse(null);
    Tester bug1 = bugRepository.findById(1L).orElse(null).getTester();
    System.out.println(
        "Device 1: Testers: "
            + mobile.getTesters().stream().map(Tester::getFirstName).collect(Collectors.toList()));
    System.out.println(
        "Device 2: Testers: "
            + mobile2.getTesters().stream().map(Tester::getFirstName).collect(Collectors.toList()));
    System.out.println(
        "Device 3: Testers: "
            + mobile3.getTesters().stream().map(Tester::getFirstName).collect(Collectors.toList()));

    System.out.println("Bug 1: Get Tester: " + bug1.getFirstName());

    System.out.println(
        "Experienced Testers "
            + testerService.getMostExperiencedTesters(Country.GB, "3").stream()
                .map(TesterDto::getFirstName)
                .collect(Collectors.toSet()));
  }
}
