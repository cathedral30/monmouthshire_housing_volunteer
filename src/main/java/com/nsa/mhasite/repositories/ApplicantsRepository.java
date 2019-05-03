package com.nsa.mhasite.repositories;

import com.nsa.mhasite.domain.VolunteerApplicant;

import java.util.List;

public interface ApplicantsRepository {
    public List<VolunteerApplicant> findAll();
    public VolunteerApplicant findByVolunteerID(Long id);
    public Integer deleteByVolunteerID(Long id);
}


