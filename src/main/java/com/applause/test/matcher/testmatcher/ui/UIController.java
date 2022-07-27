package com.applause.test.matcher.testmatcher.ui;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class UIController {

  @GetMapping(value = "/")
  public ModelAndView homePage() {
    return new ModelAndView("index");
  }
}
