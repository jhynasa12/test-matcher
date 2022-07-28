package com.applause.test.matcher.testmatcher.tester;

import com.applause.test.matcher.testmatcher.bug.BugService;
import com.applause.test.matcher.testmatcher.device.*;
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
  private final BugService bugService;

  @Autowired
  public TesterServiceImpl(
      TesterRepository testerRepository, DeviceService deviceService, BugService bugService) {
    this.testerRepository = testerRepository;
    this.deviceService = deviceService;
    this.bugService = bugService;
  }

  @Override
  public List<TesterDto> getMostExperiencedTesters(Set<String> countries, Set<Long> deviceIds) {

    List<TesterDto> experiencedTesters = new ArrayList<>();
    List<Tester> testersFromDB;

    // grab all testers by default if no country is specified
    testersFromDB = testerRepository.findAll();

    if (countries != null && !countries.isEmpty()) {
      // get testers from the specified countries
      logger.debug("Getting testers by country: {}", countries);
      Set<Country> convertedCountries =
          countries.stream().map(Country::valueOf).collect(Collectors.toSet());
      testersFromDB = testerRepository.findAllByCountryIn(convertedCountries);
    }

    Set<Long> finalDeviceIds =
        deviceIds != null && !deviceIds.contains(0L)
            ? deviceIds
            : deviceService.getAllDevices().stream()
                .map(BaseDevice::getDeviceId)
                .collect(Collectors.toSet());

    testersFromDB.forEach(
        tester -> {
          // remove bugs that are not in device Ids
          tester.getBugs().removeIf(bug -> !finalDeviceIds.contains(bug.getDeviceId()));

          // remove devices that are not in device ids
          tester
              .getMobileDevices()
              .removeIf(device -> !finalDeviceIds.contains(device.getDeviceId()));

          // map to Dtos
          experiencedTesters.add(mapToTestDto(tester));
        });

    experiencedTesters.removeIf(testerDto -> testerDto.getMobileDevices().isEmpty());

    experiencedTesters.forEach(
        testerDto -> {
          // get number of bugs for device
          testerDto
              .getMobileDevices()
              .forEach(
                  device -> {
                    int numberOfBugsForDevice =
                        bugService.getNumberOfBugsFiledByTesterWithDevice(
                            Long.valueOf(testerDto.getTesterId()),
                            Long.valueOf(device.getDeviceId()));
                    device.setNumberOfBugsByTester(numberOfBugsForDevice);
                  });
          
          // order the testers with the highest bug count for that device
          testerDto
              .getMobileDevices()
              .sort(Comparator.comparing(MobileDto::getNumberOfBugsByTester).reversed());
        });

    List<TesterDto> finalList =
        experiencedTesters.stream()
            .sorted(Comparator.comparing(testerDto -> testerDto.getBugs().size()))
            .collect(Collectors.toList());

    Collections.reverse(finalList);

    return finalList;
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
        DeviceServiceImpl.mapToMobileDto(tester.getMobileDevices()),
        bugService.mapBugDtos(tester.getBugs()));
  }
}
