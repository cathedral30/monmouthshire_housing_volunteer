package com.nsa.mhasite.repositories;

import com.nsa.mhasite.domain.Reward;

import java.util.List;

public interface RewardRepository {
    List<Reward> findAll();
    public Integer createNewOffer(Reward reward, String creator);
    public Reward findById(Long id);
    public Integer redeemReward(Long userId, Long rewardId);
    public List<Reward> findRewardsBySearch(String searchTerm);
    public List<Reward> findAllByTotal();
    public List<Reward> findAllAlphabetically();
    public Integer deleteByRewardID(Long id);
    public Integer enableByRewardID(Long id);
    public Integer increaseNumTimesRedeemed(Long id);
}
