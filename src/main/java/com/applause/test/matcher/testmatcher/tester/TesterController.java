package com.applause.test.matcher.testmatcher.tester;

import com.applause.test.matcher.testmatcher.api.TesterApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Set;

@RestController
public class TesterController implements TesterApi {

  private final TesterService testerService;

  @Autowired
  public TesterController(TesterService testerService) {
    this.testerService = testerService;
  }

  @Override
  @ModelAttribute("testers")
  public ModelAndView getMostExperiencedTesters(Set<String> countries, Set<Long> deviceIds) {

    ModelAndView modelAndView = new ModelAndView("index");
    modelAndView.addObject("testers",  testerService.getMostExperiencedTesters(countries, deviceIds));

    return modelAndView;
  }
}
