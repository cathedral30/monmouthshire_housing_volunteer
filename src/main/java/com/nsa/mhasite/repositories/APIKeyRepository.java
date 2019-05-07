package com.nsa.mhasite.repositories;

import com.nsa.mhasite.domain.APIKey;

import java.util.List;

public interface APIKeyRepository {
    public List<APIKey> findAll();
}
