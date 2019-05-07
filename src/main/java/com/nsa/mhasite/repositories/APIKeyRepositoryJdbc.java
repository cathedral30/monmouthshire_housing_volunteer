package com.nsa.mhasite.repositories;

import com.nsa.mhasite.domain.APIKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class APIKeyRepositoryJdbc implements APIKeyRepository{

    private JdbcTemplate jdbcTemplate;
    private RowMapper<APIKey> apiMapper;

    @Value("select * from api_keys;")
    String findAll;

    public APIKeyRepositoryJdbc(JdbcTemplate aTemplate){
        jdbcTemplate = aTemplate;

        apiMapper = (rs, i) -> new APIKey(
                rs.getString("user_reference"),
                rs.getString("key")
        );
    }

    @Override
    public List<APIKey> findAll() {
        return jdbcTemplate.query(
                findAll,
                new Object[]{},
                apiMapper);
    }
}
