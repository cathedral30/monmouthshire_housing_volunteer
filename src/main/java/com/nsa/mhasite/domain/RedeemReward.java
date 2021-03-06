package com.nsa.mhasite.domain;

import lombok.Data;

import java.util.Date;

@Data
public class RedeemReward {
    private String code;
    private String name;
    private String description;
    private Date date;

    public RedeemReward(Redemption redemption, Reward reward) {
        this.code = String.format("%06d", redemption.getId());
        this.name = reward.getName();
        this.description = reward.getDescription();
        this.date = redemption.getRedeption_date();
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Date getDate() {
        return date;
    }
}
