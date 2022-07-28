package com.applause.test.matcher.testmatcher.tester;

import com.applause.test.matcher.testmatcher.bug.Bug;
import com.applause.test.matcher.testmatcher.bug.BugService;
import com.applause.test.matcher.testmatcher.device.DeviceService;
import com.applause.test.matcher.testmatcher.device.Mobile;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;

@RunWith(SpringRunner.class)
public class TesterServiceImplTest {

  @Mock private TesterRepository testerRepository;
  @Mock
  private DeviceService deviceService;
  @Mock
  private BugService bugService;

  @InjectMocks private TesterServiceImpl testerServiceImpl;

  @Test
  public void shouldReturnExperiencedTesterList() {

    List<Tester> testerList = createTesterList();
    List<TesterDto> experiencedTesters = new ArrayList<>();

    testerList.forEach(tester -> experiencedTesters.add(testerServiceImpl.mapToTestDto(tester)));

    Set<Long> deviceIds = Stream.of(23L, 24L).collect(Collectors.toSet());
    Set<Country> countries = Stream.of(Country.US).collect(Collectors.toSet());
    Set<String> countriesString = Stream.of(Country.US.toString()).collect(Collectors.toSet());

    List<TesterDto> testersByUS =
        experiencedTesters.stream()
            .filter(testerDto -> Objects.equals(testerDto.getCountry(), Country.US.toString()))
            .collect(Collectors.toList());

    List<Tester> fiterList =
        testerList.stream()
            .filter(tester -> countries.contains(tester.getCountry()))
            .collect(Collectors.toList());

    doReturn(fiterList).when(testerRepository).findAllByCountryIn(countries);

    doReturn(testersByUS).when(testerServiceImpl).getMostExperiencedTesters(countriesString, deviceIds);


    int expectedResult = 2;
    int actualResult = fiterList.size();

    String expectedResult2 = "1";
    String actualResult2 = testersByUS.get(0).getTesterId();

    assertEquals(expectedResult, actualResult);

    assertEquals(expectedResult2, actualResult2);
  }

  private List<Tester> createTesterList() {

    Tester tester1 = new Tester();
    Tester tester2 = new Tester();
    Tester tester3 = new Tester();
    Tester tester4 = new Tester();
    Tester tester5 = new Tester();
    Tester tester6 = new Tester();

    tester1.setTesterId(1L);
    tester2.setTesterId(2L);
    tester3.setTesterId(3L);
    tester4.setTesterId(4L);
    tester5.setTesterId(5L);
    tester6.setTesterId(6L);

    tester1.setFirstName("Justin");
    tester2.setFirstName("George");
    tester3.setFirstName("Mary");
    tester4.setFirstName("Mike");
    tester5.setFirstName("Jane");
    tester6.setFirstName("John");

    tester1.setLastName("Hyland");
    tester2.setLastName("Doe");
    tester3.setLastName("Der");
    tester4.setLastName("Dine");
    tester5.setLastName("Smith");
    tester6.setLastName("Dig");

    tester1.setMobileDevices(createDeviceList());
    tester2.setMobileDevices(createDeviceList());
    tester3.setMobileDevices(createDeviceList());
    tester4.setMobileDevices(createDeviceList());
    tester5.setMobileDevices(createDeviceList());
    tester6.setMobileDevices(createDeviceList());

    tester1.setCountry(Country.US);
    tester2.setCountry(Country.GB);
    tester3.setCountry(Country.GB);
    tester4.setCountry(Country.US);
    tester5.setCountry(Country.JP);
    tester6.setCountry(Country.JP);

    return Arrays.asList(tester1, tester2, tester3, tester4, tester5, tester6);
  }

  private List<Mobile> createDeviceList() {

    Mobile mobile1 = new Mobile();
    mobile1.setDeviceId(23L);
    mobile1.setDescription("iPhone 4");
    Mobile mobile2 = new Mobile();
    mobile1.setDeviceId(24L);
    mobile1.setDescription("iPhone 4S");
    Mobile mobile3 = new Mobile();
    mobile1.setDeviceId(21L);
    mobile1.setDescription("iPhone 5");

    return Arrays.asList(mobile1, mobile2, mobile3);
  }

  private List<Bug> createBugList() {

    Bug bug1 = new Bug();
    bug1.setBugId(23L);
    bug1.setTesterId(1L);
    bug1.setDeviceId(23L);
    Bug bug2 = new Bug();
    bug2.setBugId(32L);
    bug2.setTesterId(1L);
    bug2.setDeviceId(21L);
    Bug bug3 = new Bug();
    bug3.setBugId(12L);
    bug3.setTesterId(2L);
    bug3.setDeviceId(24L);
    Bug bug4 = new Bug();
    bug4.setBugId(14L);
    bug4.setTesterId(3L);
    bug4.setDeviceId(23L);

    return Arrays.asList(bug1, bug2, bug3, bug4);
  }
}
