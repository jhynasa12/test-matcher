package com.applause.test.matcher.testmatcher.tester;

import java.util.List;
import java.util.Set;

public interface TesterService {

  List<TesterDto> getMostExperiencedTesters(Set<String> countries, Set<Long> deviceIds);

  void saveAllTesters(List<Tester> tester);

  Tester getTester(Long testerId);
}
