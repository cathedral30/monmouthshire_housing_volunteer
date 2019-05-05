package com.nsa.mhasite.repositories;

import com.nsa.mhasite.domain.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRoleRepositoryJdbc implements UserRoleRepository {
    private JdbcTemplate jdbcTemplate;
    private RowMapper<UserRole> userRoleMapper;

    @Value("select a.role from user_role a, user b where b.username=? and a.userid=b.id")
    String findRoleByUsername;

    @Value("${sql.create.user.role}")
    String userRoleInsertSQL;

    @Autowired
    public UserRoleRepositoryJdbc(JdbcTemplate aTemplate) {
        jdbcTemplate = aTemplate;

        userRoleMapper = (rs, i) -> new UserRole(
                rs.getLong("id"),
                rs.getLong("userid"),
                rs.getString("role")
        );
    }

    @Override
    public Integer createUserRole(Long userid, String role) {
        ArrayList<Object> params = new ArrayList<Object>();
        params.add(userid);
        params.add(role);
        return jdbcTemplate.update(userRoleInsertSQL, params.toArray());
    }

    @Override
    public List<String> findRoleByUsername(String username) {
        return jdbcTemplate.queryForList(findRoleByUsername, new Object[]{username}, String.class);
    }
}
