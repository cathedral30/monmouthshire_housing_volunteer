package com.nsa.mhasite.services;

import com.nsa.mhasite.domain.*;
import com.nsa.mhasite.email.EmailSessionBean;
import com.nsa.mhasite.repositories.*;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
public class RedeemService {
    static final Logger LOG = LoggerFactory.getLogger(RedeemService.class);
    private VolunteerRepository volunteerRepository;
    private UserRepositoryJdbc userRepositoryJdbc;
    private RewardRepository rewardRepository;
    private BusinessRepository businessRepository;
    private RedemptionRepository redemptionRepository;
    private EmailSessionBean emailSessionBean;

    @Autowired
    public RedeemService(VolunteerRepository vRepo, UserRepositoryJdbc uRepo, RewardRepository oRepo, BusinessRepository bRepo, RedemptionRepository rRepo, EmailSessionBean eMail) {
        this.volunteerRepository = vRepo;
        this.userRepositoryJdbc = uRepo;
        this.rewardRepository = oRepo;
        this.businessRepository = bRepo;
        this.redemptionRepository = rRepo;
        this.emailSessionBean = eMail;
    }


    public String redeemReward(Long rewardId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            User user = userRepositoryJdbc.findUserByUsername(currentUserName).get();
            VolunteerUser volunteer = volunteerRepository.findVolunteerByUserId(user.getId()).get();
            Reward reward = rewardRepository.findById(rewardId);
            Business business = businessRepository.findBusinessById(reward.getBusinessID());
            if (volunteer.getCurrent_balance() - reward.getCost() >= 0) {
                Integer rowsChanged = rewardRepository.redeemReward(user.getId(), reward.getId());
                if (rowsChanged == 1) {
                    LOG.debug("redemption successful in database");
                    List<Redemption> redemptions = redemptionRepository.findByUser_id(user.getId());
                    Long tempMax = 0L;
                    for (Redemption red : redemptions) {
                        if (red.getId() > tempMax) {
                            tempMax = red.getId();
                        }
                    }
                    String code = String.format("%06d", tempMax);
                    LOG.debug("reward code is: " + code);
                    emailSessionBean.redemptionEmailUser(user, reward, volunteer, code);
                    emailSessionBean.redemptionEmailBusiness(business, reward, volunteer, code);
                    return "Success";
                }
                return "Database error";
            }
            return "Not enough credits error";
        }
        return "User not logged in";
    }
}
