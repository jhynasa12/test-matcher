package com.applause.test.matcher.testmatcher.bug;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BugServiceImpl implements BugService {

    private BugRepository bugRepository;

    @Autowired
    public BugServiceImpl(BugRepository bugRepository) {
        this.bugRepository = bugRepository;
    }

    @Override
    public Integer getNumberOfBugsFiledByTesterWithDevice(Long testerId, Long deviceId) {
        return bugRepository.countAllByTesterIdAndDeviceId(testerId, deviceId);
    }



}
