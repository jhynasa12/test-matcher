package com.applause.test.matcher.testmatcher.api;

import com.applause.test.matcher.testmatcher.tester.TesterDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.Set;

public interface TesterApi {

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(value = "/api/v1/testers")
  List<TesterDto> getMostExperiencedTesters(
      @RequestParam(name = "countries", required = false) Set<String> countries,
      @RequestParam(name = "deviceIds", required = false) Set<Long> deviceIds);
}
