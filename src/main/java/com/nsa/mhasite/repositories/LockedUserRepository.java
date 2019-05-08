package com.nsa.mhasite.repositories;

import com.nsa.mhasite.domain.LockedUser;

import java.sql.Timestamp;
import java.util.List;

public interface LockedUserRepository {
    public LockedUser findByUsername(String username);
    public Integer createLockedUser(String username);
    public Integer setAttempts(String username, Integer attempts);
    public Integer setLocked(String username, Timestamp locked_until);
    public Integer deleteLockedUser(String username);
    public List<LockedUser> getLocked();
}
