package com.nsa.mhasite.repositories;

import com.nsa.mhasite.domain.LockedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Repository
public class LockedUserRepositoryJdbc implements LockedUserRepository {

    private JdbcTemplate jdbcTemplate;
    private RowMapper<LockedUser> userMapper;

    @Value("select * from locked_users where username = ?")
    String findByUsername;

    @Value("insert into locked_users (username, attempts, locked) value (? , 1, 0)")
    String createLocked;

    @Value("update locked_users set attempts = ? where username = ?")
    String setAttempts;

    @Value("update locked_users set locked = 1, locked_until = ? where username = ?")
    String setLocked;

    @Value("delete from locked_users where username = ?")
    String deleteLocked;

    @Value("select * from locked_users where locked = 1")
    String getLocked;

    @Autowired
    public LockedUserRepositoryJdbc(JdbcTemplate aTemplate) {
        jdbcTemplate = aTemplate;

        userMapper = (rs, i) -> new LockedUser(
                rs.getString("username"),
                rs.getInt("attempts"),
                rs.getBoolean("locked"),
                rs.getTimestamp("locked_until")
        );
    }

    @Override
    public LockedUser findByUsername(String username) {
        return jdbcTemplate.queryForObject(
                findByUsername,
                new Object[]{username},
                userMapper);
    }

    @Override
    public Integer createLockedUser(String username) {
        ArrayList<Object> params = new ArrayList<>();
        params.add(username);
        return jdbcTemplate.update(createLocked, params.toArray());
    }

    @Override
    public Integer setAttempts(String username, Integer attempts) {
        ArrayList<Object> params = new ArrayList<>();
        params.add(attempts);
        params.add(username);
        return jdbcTemplate.update(setAttempts, params.toArray());
    }

    @Override
    public Integer setLocked(String username, Timestamp locked_until) {
        ArrayList<Object> params = new ArrayList<>();
        params.add(locked_until);
        params.add(username);
        return jdbcTemplate.update(setLocked, params.toArray());
    }

    @Override
    public Integer deleteLockedUser(String username) {
        ArrayList<Object> params = new ArrayList<>();
        params.add(username);
        return jdbcTemplate.update(deleteLocked, params.toArray());
    }

    @Override
    public List<LockedUser> getLocked() {
        return jdbcTemplate.query(
                getLocked,
                new Object[]{},
                userMapper);
    }
}
