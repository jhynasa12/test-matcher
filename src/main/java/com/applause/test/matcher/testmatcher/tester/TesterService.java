package com.applause.test.matcher.testmatcher.tester;


import java.util.List;

public interface TesterService {

    List<TesterDto> getMostExperiencedTesters(Country country, String deviceId);

    void saveAllTesters(List<Tester> tester);

    Tester getTester(Long testerId);
}
