package com.nsa.mhasite.controllers;

import com.nsa.mhasite.domain.*;
import com.nsa.mhasite.repositories.VolunteerRepositoryJdbc;
import com.nsa.mhasite.services.RedeemService;
import com.nsa.mhasite.services.GiftService;
import com.nsa.mhasite.services.RewardService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/profile")
public class RewardDetailController {

    static final Logger LOG = LoggerFactory.getLogger(RewardDetailController.class);
    private RewardService rewardService;
    private RedeemService redeemService;
    private GiftService giftService;
    private VolunteerRepositoryJdbc volunteerRepositoryJdbc;

    @Autowired
    public RewardDetailController(RewardService oService, RedeemService rService, GiftService gService, VolunteerRepositoryJdbc vRepo) {
        rewardService = oService;
        redeemService = rService;
        giftService = gService;
        volunteerRepositoryJdbc = vRepo;
    }

    private VolunteerUser getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return volunteerRepositoryJdbc.findVolunteerByUsername(currentPrincipalName).get();
    }

    @RequestMapping(path = "/offers/{id}", method = RequestMethod.GET)
    public String getCharityProfile(@PathVariable Long id, Model model) {
        Reward reward = rewardService.findById(id);
        if (reward != null) {
            model.addAttribute("reward", reward);
            model.addAttribute("content", "rewardDetail");
            model.addAttribute("user", getUser());
            return "userMenu";
        } else {
            return "404";
        }
    }

    @RequestMapping(path = "/reward_redeem/{id}", method = RequestMethod.POST)
    public String redeemReward(@PathVariable Long id, Model model) {
        String report = redeemService.redeemReward(id);
        LOG.debug(report);
        Reward reward = rewardService.findById(id);
        if (reward != null) {
            model.addAttribute("offer", reward);
        } else {
            return "404";
        }
        if (report.equals("Success")) {
            Integer result = this.rewardService.increaseNumTimesRedeemed(reward.getId());
            LOG.debug("Result of increasing num times redeemed: " + result);
            model.addAttribute("success", "Successfully redeemed!");
            model.addAttribute("reward", reward);
            model.addAttribute("content", "rewardDetail");
            model.addAttribute("user", getUser());
            return "userMenu";
        } else {
            model.addAttribute("success", report);
            model.addAttribute("reward", reward);
            model.addAttribute("content", "rewardDetail");
            model.addAttribute("user", getUser());
            return "userMenu";
        }
    }

    @RequestMapping(path = "/gift/{id}", method = RequestMethod.GET)
    public String giftRewardForm(@PathVariable Long id, Model model) {
        Reward reward = rewardService.findById(id);
        if (reward != null) {
            model.addAttribute("id", id);
            model.addAttribute("content", "gifting");
            model.addAttribute("user", getUser());
            return "userMenu";
        } else {
            return "404";
        }
    }

    @RequestMapping(path = "/gift/{id}", method = RequestMethod.POST)
    public String giftReward(@PathVariable Long id, GiftForm form, Model model) {
        Reward reward = rewardService.findById(id);
        if (reward != null) {
            model.addAttribute("id", id);
            model.addAttribute("success", giftService.giftReward(form, id));
            model.addAttribute("content", "gifting");
            model.addAttribute("user", getUser());
            return "userMenu";
        } else {
            return "404";
        }
    }
}
