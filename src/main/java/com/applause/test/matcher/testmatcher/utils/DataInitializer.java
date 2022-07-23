package com.applause.test.matcher.testmatcher.utils;

import com.applause.test.matcher.testmatcher.config.DataConfig;
import com.applause.test.matcher.testmatcher.devices.Mobile;
import com.applause.test.matcher.testmatcher.user.Tester;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.stream.Collectors;

@Component
public class DataInitializer {

    private final CsvDataLoader csvDataLoader;
    private final DataConfig dataConfig;

    @Autowired
    public DataInitializer(CsvDataLoader csvDataLoader, DataConfig dataConfig) {
        this.csvDataLoader = csvDataLoader;
        this.dataConfig = dataConfig;
    }

    @PostConstruct
    private void setupData() {
        System.out.println(csvDataLoader.loadObjectList(Mobile.class, dataConfig.getDevices()).stream().map(Mobile::getDescription).collect(Collectors.toList()));
        System.out.println(csvDataLoader.loadObjectList(Tester.class, dataConfig.getTesters()).stream().map(Tester::getFirstName).collect(Collectors.toList()));
       // System.out.println(csvDataLoader.loadObjectList(Mobile.class, dataConfig.getBugs()));
    }

}
