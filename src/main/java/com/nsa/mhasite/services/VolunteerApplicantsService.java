package com.nsa.mhasite.services;

import com.nsa.mhasite.domain.VolunteerApplicant;

import java.util.List;

public interface VolunteerApplicantsService {
    public List<VolunteerApplicant> findAllVolunteers();
    public VolunteerApplicant findByVolunteerAppId(Long id);
    public Integer deleteByVolunteerID(Long id);
}
