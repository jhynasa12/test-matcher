package com.applause.test.matcher.testmatcher.bug;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BugRepository extends JpaRepository<Bug, Long> {

    List<Bug> findAllByTesterId(Long testerId);
    Integer countAllByTesterIdAndDeviceId(Long testerId, Long deviceId);

}
