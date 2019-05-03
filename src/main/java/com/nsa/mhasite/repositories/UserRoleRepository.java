package com.nsa.mhasite.repositories;

import java.util.List;

public interface UserRoleRepository {
    public Integer createUserRole(Long userid, String role);
    public List<String> findRoleByUsername(String username);
}
