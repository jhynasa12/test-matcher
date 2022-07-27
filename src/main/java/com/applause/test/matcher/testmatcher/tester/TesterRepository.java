package com.applause.test.matcher.testmatcher.tester;

import com.applause.test.matcher.testmatcher.bug.Bug;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface TesterRepository extends JpaRepository<Tester, Long> {

  List<Tester> findAllByCountryIn(Collection<Country> country);
}
