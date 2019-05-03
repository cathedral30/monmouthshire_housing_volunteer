package com.nsa.mhasite.statics;

import com.nsa.mhasite.domain.Reward;
import com.nsa.mhasite.repositories.RewardRepository;
import com.nsa.mhasite.services.RewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RewardStatic implements RewardService {
    private RewardRepository oRepo;

    @Autowired
    public RewardStatic(RewardRepository oRepo) {
        this.oRepo = oRepo;
    }

    @Override
    public List<Reward> findAll() {
        return oRepo.findAll();
    }

    @Override
    public Integer createNewOffer(Reward reward, String creator) {
        return this.oRepo.createNewOffer(reward, creator);
    }

    @Override
    public Reward findById(Long id) {
        return this.oRepo.findById(id);
    }

    @Override
    public Integer redeemReward(Long userId, Long rewardId) {
        return this.oRepo.redeemReward(userId, rewardId);
    }

    @Override
    public List<Reward> findRewardsBySearch(String searchTerm) {
        return this.oRepo.findRewardsBySearch(searchTerm);
    }

    @Override
    public List<Reward> findAllByTotal() {
        return this.oRepo.findAllByTotal();
    }

    @Override
    public List<Reward> findAllAlphabetically() {
        return this.oRepo.findAllAlphabetically();
    }

    @Override
    public Integer deleteByRewardID(Long id) {
        return this.oRepo.deleteByRewardID(id);
    }

    @Override
    public Integer enableByRewardID(Long id) {
        return this.oRepo.enableByRewardID(id);
    }

    @Override
    public Integer increaseNumTimesRedeemed(Long id) {
        return this.oRepo.increaseNumTimesRedeemed(id);
    }
}
