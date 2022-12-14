package com.applause.test.matcher.testmatcher.bug;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BugServiceImpl implements BugService {

  private final BugRepository bugRepository;

  @Autowired
  public BugServiceImpl(BugRepository bugRepository) {
    this.bugRepository = bugRepository;
  }

  @Override
  public Integer getNumberOfBugsFiledByTesterWithDevice(Long testerId, Long deviceId) {
    return bugRepository.countAllByTesterIdAndDeviceId(testerId, deviceId);
  }

  @Override
  public List<Bug> findAllByTesterId(Long testerId) {
    return bugRepository.findAllByTesterId(testerId);
  }

  @Override
  public List<BugDto> mapBugDtos(List<Bug> bugs) {
    List<BugDto> bugDtos = new ArrayList<>();

    bugs.forEach(
        bug -> {
          BugDto bugDto = new BugDto();
          bugDto.setBugId(bug.getBugId());
          bugDto.setTesterId(bug.getTesterId());
          bugDto.setDeviceId(bug.getDeviceId());
          bugDto.setTester(bug.getTester());
          bugDtos.add(bugDto);
        });
    return bugDtos;
  }
}
