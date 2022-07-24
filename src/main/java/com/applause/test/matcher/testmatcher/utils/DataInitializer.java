package com.applause.test.matcher.testmatcher.utils;

import com.applause.test.matcher.testmatcher.bug.Bug;
import com.applause.test.matcher.testmatcher.bug.BugRepository;
import com.applause.test.matcher.testmatcher.config.DataConfig;
import com.applause.test.matcher.testmatcher.device.BaseDevice;
import com.applause.test.matcher.testmatcher.device.DeviceRepository;
import com.applause.test.matcher.testmatcher.device.Mobile;
import com.applause.test.matcher.testmatcher.mapper.TesterDeviceMapper;
import com.applause.test.matcher.testmatcher.mapper.TesterDeviceMapperRepository;
import com.applause.test.matcher.testmatcher.tester.Tester;
import com.applause.test.matcher.testmatcher.tester.TesterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class DataInitializer {

    Logger logger = LoggerFactory.getLogger(DataInitializer.class);

    private final CsvDataLoader csvDataLoader;
    private final DataConfig dataConfig;
    private final DeviceRepository deviceRepository;
    private final TesterRepository testerRepository;
    private final BugRepository bugRepository;
    private final TesterDeviceMapperRepository testerDeviceMapperRepository;

    @Autowired
    public DataInitializer(CsvDataLoader csvDataLoader,
                           DataConfig dataConfig,
                           DeviceRepository deviceRepository,
                           TesterRepository testerRepository,
                           BugRepository bugRepository,
                           TesterDeviceMapperRepository testerDeviceMapperRepository) {
        this.csvDataLoader = csvDataLoader;
        this.dataConfig = dataConfig;
        this.deviceRepository = deviceRepository;
        this.testerRepository = testerRepository;
        this.bugRepository = bugRepository;
        this.testerDeviceMapperRepository = testerDeviceMapperRepository;
    }

    @PostConstruct
    private void setupData() {
        logger.info("Initializing data");
        deviceRepository.saveAll(csvDataLoader.loadObjectList(Mobile.class, dataConfig.getDevices()));
        testerRepository.saveAll(csvDataLoader.loadObjectList(Tester.class, dataConfig.getTesters()));
        bugRepository.saveAll(csvDataLoader.loadObjectList(Bug.class, dataConfig.getBugs()));
        testerDeviceMapperRepository.saveAll(csvDataLoader.loadObjectList(TesterDeviceMapper.class, dataConfig.getTesterDevice()));
        logger.info("initializing data successful");
    }

}
