package com.nsa.mhasite.domain;

import lombok.Data;

@Data
public class RewardAndOffer {
    private String businessName;
    private Reward reward;

    public RewardAndOffer(String businessName, Reward reward) {
        this.businessName = businessName;
        this.reward = reward;
    }
}
