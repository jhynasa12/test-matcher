package com.applause.test.matcher.testmatcher.tester;

import com.applause.test.matcher.testmatcher.bug.Bug;
import com.applause.test.matcher.testmatcher.bug.BugService;
import com.applause.test.matcher.testmatcher.bug.BugServiceImpl;
import com.applause.test.matcher.testmatcher.device.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TesterServiceImpl implements TesterService {
    Logger logger = LoggerFactory.getLogger(TesterServiceImpl.class);

    private final TesterRepository testerRepository;
    private final BugService bugService;

    @Autowired
    public TesterServiceImpl(TesterRepository testerRepository, BugService bugService) {
        this.testerRepository = testerRepository;
        this.bugService = bugService;
    }

    @Override
    public List<TesterDto> getMostExperiencedTesters(Country country, String deviceId) {

        List<TesterDto> experiencedTesters = new ArrayList<>();

        // first get testers from the specified country
        logger.debug("Getting testers by country: {}", country);
        List<Tester> testersList = testerRepository.findAllByCountry(country);

        // has any of these users tested this device
        testersList.forEach(tester -> {

            // get the list of the testers devices and see if they tested it
            boolean isTestedByUser = tester.getMobileDevices().stream().map(BaseDevice::getDeviceId).collect(Collectors.toSet()).contains(Long.parseLong(deviceId));
            if (isTestedByUser) {

                int numberOfBugByDevice = bugService.getNumberOfBugsFiledByTesterWithDevice(tester.getTesterId(), Long.parseLong(deviceId));

                List<Mobile> devicesById = tester.getMobileDevices().stream().filter(mobile -> mobile.getDeviceId() == Long.parseLong(deviceId)).collect(Collectors.toList());

                tester.setMobileDevices(devicesById);

                System.out.println("Tester: " + tester.getFirstName() +  " tested "+ devicesById.get(0).getDescription() + " " + numberOfBugByDevice + " times");

                experiencedTesters.add(mapToTestDto(tester));
            }
        });

        // order the users with the highest bug count for that device
       // Arrays.sort(experiencedTesters)

        return experiencedTesters;
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
        return new TesterDto(String.valueOf(tester.getTesterId()), tester.getFirstName(), tester.getLastName(), String.valueOf(tester.getCountry()), tester.getLastLogin(), DeviceServiceImpl.mapToMobileDto(tester.getMobileDevices()));
    }
}
