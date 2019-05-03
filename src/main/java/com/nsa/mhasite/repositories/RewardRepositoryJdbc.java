package com.nsa.mhasite.repositories;

import com.nsa.mhasite.domain.Reward;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RewardRepositoryJdbc implements RewardRepository {
    private JdbcTemplate jdbcTemplate;
    private RowMapper<Reward> offerMapper;

    @Value("select * from reward where active = 1")
    String findAllSQL;

    @Value("insert into reward (reward_name, reward_description, cost, business_id, num_times_redeemed, creator, active) values (?, ?, ?, ?, ?, ?, ?)")
    String insertNewRewardSQL;

    @Value("select * from reward where id =?")
    String findNameByIdSQL;

    @Value("${sql.redeem.reward}")
    String redeemRewardSQL;

    @Value("select * from reward where id LIKE ? or reward_name LIKE ? or reward_description LIKE ?")
    String findOfferBySearchSQL;

    @Value("select * from reward where active = 1 order by cost desc, reward_name asc")
    String findAllByTotalSQL;

    @Value("select * from reward where active = 1 order by reward_name asc, cost desc")
    String findAllAlphabeticallySQL;

    @Value("CALL deleteReward (?)")
    String deleteRewardByIdSQL;

    @Value("CALL increaseRewardsNumTimesRedeemed (?)")
    String increaseNumTimesRedeemedSQL;

    @Value("UPDATE reward SET active = 1 WHERE id = ?")
    String enableRewardSQL;

    @Autowired
    public RewardRepositoryJdbc(JdbcTemplate aTemplate) {
        jdbcTemplate = aTemplate;

        offerMapper = (rs, i) -> new Reward(
                rs.getLong("id"),
                rs.getString("reward_name"),
                rs.getString("reward_description"),
                rs.getInt("cost"),
                rs.getLong("business_id"),
                rs.getInt("num_times_redeemed"),
                rs.getString("creator"),
                rs.getBoolean("active")
        );
    }

    @Override
    public List<Reward> findAll() {
        return jdbcTemplate.query(
                findAllSQL,
                new Object[]{},
                offerMapper);
    }

    @Override
    public Integer createNewOffer(Reward reward, String creator) {
        ArrayList<Object> params = new ArrayList<Object>();
        params.add(reward.getName());
        params.add(reward.getDescription());
        params.add(reward.getCost());
        params.add(reward.getBusinessID());
        params.add(0);
        params.add(creator);
        params.add(1);
        return jdbcTemplate.update(insertNewRewardSQL, params.toArray());
    }

    @Override
    public Reward findById(Long id) {
        return jdbcTemplate.queryForObject(
                        findNameByIdSQL,
                        new Object[]{id},
                        offerMapper);
    }

    @Override
    public Integer redeemReward(Long userId, Long rewardId) {
        ArrayList<Object> params = new ArrayList<Object>();
        params.add(userId);
        params.add(rewardId);
        return jdbcTemplate.update(redeemRewardSQL, params.toArray());
    }

    @Override
    public List<Reward> findRewardsBySearch(String searchTerm) {
        return jdbcTemplate.query(
                findOfferBySearchSQL,
                new Object[]{searchTerm, "%" + searchTerm + "%", searchTerm},
                offerMapper);
    }

    @Override
    public List<Reward> findAllByTotal() {
        return jdbcTemplate.query(
                findAllByTotalSQL,
                new Object[]{},
                offerMapper);
    }

    @Override
    public List<Reward> findAllAlphabetically() {
        return jdbcTemplate.query(
                findAllAlphabeticallySQL,
                new Object[]{},
                offerMapper);
    }

    @Override
    public Integer deleteByRewardID(Long id) {
        ArrayList<Object> params = new ArrayList<Object>();
        params.add(id);
        return jdbcTemplate.update(deleteRewardByIdSQL, params.toArray());
    }

    @Override
    public Integer enableByRewardID(Long id) {
        ArrayList<Object> params = new ArrayList<>();
        params.add(id);
        return jdbcTemplate.update(enableRewardSQL, params.toArray());
    }

    @Override
    public Integer increaseNumTimesRedeemed(Long id) {
        ArrayList<Object> params = new ArrayList<Object>();
        params.add(id);
        return jdbcTemplate.update(increaseNumTimesRedeemedSQL, params.toArray());
    }
}
