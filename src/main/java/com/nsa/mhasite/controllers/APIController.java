package com.nsa.mhasite.controllers;

import com.nsa.mhasite.domain.Business;
import com.nsa.mhasite.domain.VolunteerUser;
import com.nsa.mhasite.repositories.BusinessRepository;
import com.nsa.mhasite.repositories.VolunteerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api")
public class APIController {

    private VolunteerRepository vRepo;
    private BusinessRepository bRepo;

    @Autowired
    public APIController(VolunteerRepository vRepo, BusinessRepository bRepo){
        this.vRepo = vRepo;
        this.bRepo = bRepo;
    }

    @RequestMapping(value = "volunteer", method = RequestMethod.GET, produces = "application/json")
    public List<VolunteerUser> apiVolunteer(@RequestParam(required = false) String fName, @RequestParam(required = false) String sName){
        if (sName != null){
            return vRepo.findVolunteerBySearch(sName);
        }
        if (fName != null){
            return vRepo.findVolunteerBySearch(fName);
        }
        else {
            return vRepo.findVolunteerBySearch("");
        }
    }

    @RequestMapping(value = "business")
    public List<Business> apiBusiness(@RequestParam(required = false) String name){
        if (name != null) {
            List<Business> businesses = new ArrayList<>();
            businesses.add(bRepo.findBusinessById(bRepo.findBusinessIdByName(name)));
            return businesses;
        }
        else {
            return bRepo.findAll();
        }
    }
}
