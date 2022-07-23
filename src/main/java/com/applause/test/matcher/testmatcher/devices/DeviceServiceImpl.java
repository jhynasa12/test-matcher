package com.applause.test.matcher.testmatcher.devices;

import com.applause.test.matcher.testmatcher.utils.CsvDataLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceServiceImpl implements DeviceService {

    private final CsvDataLoader csvDataLoader;

    @Autowired
    public DeviceServiceImpl(CsvDataLoader csvDataLoader) {
        this.csvDataLoader = csvDataLoader;
    }

    @Override
    public List<BaseDevice> getAllDevices() {
     return null;
    }
}
