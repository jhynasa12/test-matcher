package com.applause.test.matcher.testmatcher.tester;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.OverrideAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(TesterController.class)
@OverrideAutoConfiguration(enabled = true)
public class TesterControllerTest {

  @Autowired private MockMvc mvc;

  @MockBean
  private TesterService testerService;

  @Test
  public void shouldReturnListOfTesterDtos() throws Exception {

    Set<String> countries = Stream.of("US").collect(Collectors.toSet());
    Set<Long> deviceIds = Stream.of(1L, 2L).collect(Collectors.toSet());

//    when(testerService.getMostExperiencedTesters(countries,deviceIds)).thenReturn()
//            String url = "/api/v1/testers?countries=" + countries +"?deviceIds=" + deviceIds;

  }
}
