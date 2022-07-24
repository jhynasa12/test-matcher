package com.applause.test.matcher.testmatcher.utils;

import com.applause.test.matcher.testmatcher.bug.Bug;
import com.applause.test.matcher.testmatcher.config.DataConfig;
import com.applause.test.matcher.testmatcher.device.DeviceRepository;
import com.applause.test.matcher.testmatcher.device.Mobile;
import com.applause.test.matcher.testmatcher.user.Tester;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DataInitializer {

    Logger logger = LoggerFactory.getLogger(DataInitializer.class);

    private final CsvDataLoader csvDataLoader;
    private final DataConfig dataConfig;
    private final DeviceRepository deviceRepository;

    @Autowired
    public DataInitializer(CsvDataLoader csvDataLoader, DataConfig dataConfig, DeviceRepository deviceRepository) {
        this.csvDataLoader = csvDataLoader;
        this.dataConfig = dataConfig;
        this.deviceRepository = deviceRepository;
    }

    @PostConstruct
    private void setupData() {
        logger.info("Initializing data");

        List<Mobile> mobileDeviceList = csvDataLoader.loadObjectList(Mobile.class, dataConfig.getDevices());

        deviceRepository.saveAll(mobileDeviceList);

        deviceRepository.saveAll(csvDataLoader.loadObjectList(Mobile.class, dataConfig.getDevices()));
        System.out.println(csvDataLoader.loadObjectList(Tester.class, dataConfig.getTesters()).stream().map(Tester::getFirstName).collect(Collectors.toList()));
        System.out.println(csvDataLoader.loadObjectList(Bug.class, dataConfig.getBugs()).stream().map(Bug::getBugId).collect(Collectors.toList()));
        logger.info("initializing data successful");
    }

}
