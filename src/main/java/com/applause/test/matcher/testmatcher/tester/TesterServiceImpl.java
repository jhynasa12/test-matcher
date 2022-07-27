package com.applause.test.matcher.testmatcher.tester;

import com.applause.test.matcher.testmatcher.bug.Bug;
import com.applause.test.matcher.testmatcher.bug.BugService;
import com.applause.test.matcher.testmatcher.device.*;
import com.applause.test.matcher.testmatcher.mapper.TesterDeviceMapper;
import com.applause.test.matcher.testmatcher.utils.Utility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TesterServiceImpl implements TesterService {
  Logger logger = LoggerFactory.getLogger(TesterServiceImpl.class);

  private final TesterRepository testerRepository;
  private final DeviceService deviceService;

  @Autowired
  public TesterServiceImpl(TesterRepository testerRepository, DeviceService deviceService) {
    this.testerRepository = testerRepository;
    this.deviceService = deviceService;
  }

  @Override
  public List<TesterDto> getMostExperiencedTesters(Set<String> countries, Set<Long> deviceIds) {

    List<TesterDto> experiencedTesters = new ArrayList<>();
    List<Tester> testersList;

    testersList = testerRepository.findAll();

    if ((countries != null && !countries.isEmpty())
        && (deviceIds != null && !deviceIds.isEmpty())) {

      // first get testers from the specified country or countries
      logger.debug("Getting testers by country: {}", countries);
      Set<Country> convertedCountries =
          countries.stream().map(Country::valueOf).collect(Collectors.toSet());
      testersList = testerRepository.findAllByCountryIn(convertedCountries);
    }

    if (deviceIds != null && deviceIds.contains(0L)) {
      deviceIds =
          deviceService.getAllDevices().stream()
              .map(BaseDevice::getDeviceId)
              .collect(Collectors.toSet());
    }
    processTesterBugInformation(testersList, deviceIds);

    testersList.forEach(tester -> experiencedTesters.add(mapToTestDto(tester)));

    // order the testers with the highest bug count for that device
    experiencedTesters.forEach(
        tester ->
            tester
                .getMobileDevices()
                .sort(Comparator.comparing(MobileDto::getNumberOfBugsByTester).reversed()));

    return experiencedTesters;
  }

  private void processTesterBugInformation(List<Tester> testers, Set<Long> deviceIds) {

    // if there are no device ids requested get All by default
    if (deviceIds == null || deviceIds.isEmpty()) {
      deviceIds =
          deviceService.getAllDevices().stream()
              .map(BaseDevice::getDeviceId)
              .collect(Collectors.toSet());
    }
    // we have the testers for specified countries
    // now get each tester's bugs for the specified devices
    Set<Long> finalDeviceIds = deviceIds;
    testers.forEach(
        tester -> {
          // set the number of bugs the tester found for each device
          finalDeviceIds.forEach(
              deviceId -> {
                List<Bug> bugsByDevice =
                    tester.getBugs().stream()
                        .filter(bug -> Objects.equals(bug.getDeviceId(), deviceId))
                        .collect(Collectors.toList());
                BaseDevice device =
                    tester.getMobileDevices().stream()
                        .filter(mobile -> Objects.equals(mobile.getDeviceId(), deviceId))
                        .collect(Utility.toSingleton());
                if (device != null) {
                  device.setNumberOfBugsByTester(bugsByDevice.size());
                }
              });
        });
  }

  @Override
  public void saveAllTesters(List<Tester> testers) {
    testerRepository.saveAll(testers);
  }

  @Override
  public Tester getTester(Long testerId) {
    return testerRepository.findById(testerId).orElse(null);
  }

  private TesterDto mapToTestDto(Tester tester) {
    return new TesterDto(
        String.valueOf(tester.getTesterId()),
        tester.getFirstName(),
        tester.getLastName(),
        String.valueOf(tester.getCountry()),
        tester.getLastLogin(),
        DeviceServiceImpl.mapToMobileDto(tester.getMobileDevices()));
  }
}
