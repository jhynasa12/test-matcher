package com.applause.test.matcher.testmatcher.Bug;

import com.applause.test.matcher.testmatcher.bug.Bug;
import com.applause.test.matcher.testmatcher.bug.BugRepository;
import com.applause.test.matcher.testmatcher.bug.BugServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;

@RunWith(SpringRunner.class)
public class BugServiceImplTest {

  @Mock private BugRepository bugRepository;

  @InjectMocks BugServiceImpl bugServiceImp;

  @Test
  public void shouldReturnAllBugsByTesterId() {

    List<Bug> bugList = createBugList();
    Long testerId = 55L;

    doReturn(bugList).when(bugRepository).findAllByTesterId(testerId);

    Long expectedResult = 23L;
    Long actualResult = bugList.get(0).getBugId();

    assertEquals(expectedResult, actualResult);
  }

  @Test
  public void shouldReturnNumberOfBugs() {

    List<Bug> bugList = createBugList();

    Long deviceId = 22L;
    Long testerId = 55L;

    List<Bug> filteredBugList =
        bugList.stream()
            .filter(bug -> Objects.equals(bug.getDeviceId(), deviceId))
            .collect(Collectors.toList());

    doReturn(filteredBugList.size())
        .when(bugRepository)
        .countAllByTesterIdAndDeviceId(testerId, deviceId);

    int expectedResult = 1;
    int actualResult = filteredBugList.size();

    assertEquals(expectedResult, actualResult);
  }

  private List<Bug> createBugList() {

    Bug bug1 = new Bug();
    bug1.setBugId(23L);
    bug1.setTesterId(55L);
    bug1.setDeviceId(34L);
    Bug bug2 = new Bug();
    bug2.setBugId(32L);
    bug2.setTesterId(55L);
    bug2.setDeviceId(22L);
    Bug bug3 = new Bug();
    bug3.setBugId(12L);
    bug3.setTesterId(55L);
    bug3.setDeviceId(53L);

    return Arrays.asList(bug1, bug2, bug3);
  }
}
