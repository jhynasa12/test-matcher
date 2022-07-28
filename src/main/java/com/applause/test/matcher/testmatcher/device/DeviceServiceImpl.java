package com.applause.test.matcher.testmatcher.device;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeviceServiceImpl implements DeviceService {

  private final DeviceRepository deviceRepository;

  @Autowired
  public DeviceServiceImpl(DeviceRepository deviceRepository) {
    this.deviceRepository = deviceRepository;
  }

  @Override
  public List<BaseDevice> getAllDevices() {
    return deviceRepository.findAll();
  }

  @Override
  public BaseDevice getDevice(Long deviceId) {
    return deviceRepository.findById(deviceId).orElse(null);
  }

  public static List<MobileDto> mapToMobileDto(List<Mobile> mobileList) {
    List<MobileDto> mobileDtos = new ArrayList<>();
    mobileList.forEach(
        mobile ->
            mobileDtos.add(
                new MobileDto(
                    String.valueOf(mobile.getDeviceId()),
                    mobile.getDescription(),
                    mobile.getNumberOfBugsByTester())));
    return mobileDtos;
  }
}
