package com.nsa.mhasite.services;

import com.nsa.mhasite.repositories.UserRepository;
import com.nsa.mhasite.repositories.VolunteerRepositoryJdbc;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class ChangePhoneService {
    private UserRepository userRepository;
    private VolunteerRepositoryJdbc volunteerRepositoryJdbc;

    @Autowired
    ChangePhoneService(UserRepository uRepo, VolunteerRepositoryJdbc vRepo) {
        userRepository = uRepo;
        volunteerRepositoryJdbc = vRepo;
    }

    public Integer changePhones(String phone, String emergencyPhone) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            Long userId = userRepository.findUserByUsername(currentUserName).get().getId();
            Integer rowsChanged = volunteerRepositoryJdbc.changeNumber(userId, phone);
            rowsChanged += volunteerRepositoryJdbc.changeEmergencyNumber(userId, emergencyPhone);
            return rowsChanged;
        }
        return 0;
    }
}
