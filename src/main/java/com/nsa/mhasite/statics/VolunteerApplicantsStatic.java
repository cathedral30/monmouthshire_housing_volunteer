package com.nsa.mhasite.statics;

import com.nsa.mhasite.domain.VolunteerApplicant;
import com.nsa.mhasite.repositories.ApplicantsRepository;
import com.nsa.mhasite.services.VolunteerApplicantsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VolunteerApplicantsStatic implements VolunteerApplicantsService {

    private ApplicantsRepository volunteerApplicantsRepo;

    @Autowired
    public VolunteerApplicantsStatic(ApplicantsRepository volunteerApplicantsRepo) {
        this.volunteerApplicantsRepo = volunteerApplicantsRepo;
    }

    @Override
    public List<VolunteerApplicant> findAllVolunteers() {
        return this.volunteerApplicantsRepo.findAll();
    }

    @Override
    public VolunteerApplicant findByVolunteerAppId(Long id) {
        return this.volunteerApplicantsRepo.findByVolunteerID(id);
    }

    @Override
    public Integer deleteByVolunteerID(Long id) {
        return this.volunteerApplicantsRepo.deleteByVolunteerID(id);
    }
}
