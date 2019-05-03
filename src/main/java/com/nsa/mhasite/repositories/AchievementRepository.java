package com.nsa.mhasite.repositories;

import com.nsa.mhasite.domain.Achievement;

import java.util.List;
import java.util.Optional;

public interface AchievementRepository {
    List<Achievement> missingAchievements(Long userid);
    Integer awardAchievement(Long userid, Long achievementid);
    Optional<Achievement> findById(Long achievementid);
}
