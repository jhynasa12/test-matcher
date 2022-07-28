package com.applause.test.matcher.testmatcher.bug;

import java.util.List;

public interface BugService {

  List<Bug> findAllByTesterId(Long testerId);
  Integer getNumberOfBugsFiledByTesterWithDevice(Long testId, Long deviceId);
  List<BugDto> mapBugDtos(List<Bug> bug);
}
