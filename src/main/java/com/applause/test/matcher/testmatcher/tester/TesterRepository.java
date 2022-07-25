package com.applause.test.matcher.testmatcher.tester;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TesterRepository extends JpaRepository<Tester, Long> {

    List<Tester> findAllByCountry(Country country);
}
