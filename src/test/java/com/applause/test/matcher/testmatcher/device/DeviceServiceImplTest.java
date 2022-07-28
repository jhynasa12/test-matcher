package com.applause.test.matcher.testmatcher.device;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;

@RunWith(SpringRunner.class)
public class DeviceServiceImplTest {

  @Mock private DeviceRepository deviceRepository;

  @InjectMocks private DeviceServiceImpl deviceService;

  @Test
  public void shouldReturnAllMobilesByTesterId() {

    List<BaseDevice> deviceList = createDeviceList();
    Long testerId = 55L;

    doReturn(deviceList).when(deviceRepository).findAll();

    int expectedResult = 3;
    int actualResult = deviceList.size();

    assertEquals(expectedResult, actualResult);
  }

  private List<BaseDevice> createDeviceList() {

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
}
