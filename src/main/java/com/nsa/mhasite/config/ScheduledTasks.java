package com.nsa.mhasite.config;

import com.nsa.mhasite.domain.LockedUser;
import com.nsa.mhasite.repositories.LockedUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;

@Component
public class ScheduledTasks {

    private LockedUserRepository lRepo;

    @Autowired
    public ScheduledTasks(LockedUserRepository lRepo) {
        this.lRepo = lRepo;
    }


    @Scheduled(fixedRate = 10000)
    public void checkLocks() {
        List<LockedUser> users = lRepo.getLocked();
        Timestamp time = new Timestamp(System.currentTimeMillis());

        for (LockedUser user : users) {
            if (user.getLocked_until().before(time)) {
                lRepo.deleteLockedUser(user.getUsername());
            }
        }
    }
}
