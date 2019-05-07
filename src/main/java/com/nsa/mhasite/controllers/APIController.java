package com.nsa.mhasite.controllers;

import com.nsa.mhasite.domain.APIKey;
import com.nsa.mhasite.domain.Business;
import com.nsa.mhasite.domain.VolunteerUser;
import com.nsa.mhasite.repositories.APIKeyRepository;
import com.nsa.mhasite.repositories.BusinessRepository;
import com.nsa.mhasite.repositories.VolunteerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api")
public class APIController {

    private VolunteerRepository vRepo;
    private BusinessRepository bRepo;
    private APIKeyRepository aRepo;

    @Autowired
    public APIController(VolunteerRepository vRepo, BusinessRepository bRepo, APIKeyRepository aRepo){
        this.vRepo = vRepo;
        this.bRepo = bRepo;
        this.aRepo = aRepo;
    }

    private Boolean checkKey(String key){
        List<APIKey> allKeys = aRepo.findAll();

        for (APIKey apikey : allKeys){
            if (apikey.getKey().equals(key)){
                return Boolean.TRUE;
            }
        }
        return  Boolean.FALSE;
    }

    @RequestMapping(value = "volunteer", method = RequestMethod.GET, produces = "application/json")
    public List<VolunteerUser> apiVolunteer(@RequestHeader("key") String apiKey, @RequestParam(required = false) String fName, @RequestParam(required = false) String sName){
        if (checkKey(apiKey)){
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
        else {
            return new ArrayList<>();
        }
    }

    @RequestMapping(value = "business")
    public List<Business> apiBusiness(@RequestHeader("key") String apiKey, @RequestParam(required = false) String name){
        if (checkKey(apiKey)) {
            if (name != null) {
                List<Business> businesses = new ArrayList<>();
                businesses.add(bRepo.findBusinessById(bRepo.findBusinessIdByName(name)));
                return businesses;
            } else {
                return bRepo.findAll();
            }
        }
        else {
            return new ArrayList<>();
        }
    }
}
