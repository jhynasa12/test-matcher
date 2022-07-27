package com.applause.test.matcher.testmatcher.tester;

import com.applause.test.matcher.testmatcher.api.TesterApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
public class TesterController implements TesterApi {

  private final TesterService testerService;

  @Autowired
  public TesterController(TesterService testerService) {
    this.testerService = testerService;
  }

  @Override
  public List<TesterDto> getMostExperiencedTesters(Set<String> countries, Set<Long> deviceIds) {
    return testerService.getMostExperiencedTesters(countries, deviceIds);
  }
}
