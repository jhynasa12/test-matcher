package com.applause.test.matcher.testmatcher.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import java.util.Set;

public interface TesterApi {

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(value = "/api/v1/testers")
  ModelAndView getMostExperiencedTesters(
      @RequestParam(name = "countries", required = false) Set<String> countries,
      @RequestParam(name = "deviceIds", required = false) Set<Long> deviceIds);
}
