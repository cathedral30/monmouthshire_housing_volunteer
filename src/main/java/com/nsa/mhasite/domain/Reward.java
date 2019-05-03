package com.nsa.mhasite.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "reward")
public class Reward {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "reward_name")
    private String name;

    @Column(name = "reward_description")
    private String description;

    @Column(name = "cost")
    private int cost;

    @Column(name = "business_id")
    private Long businessID;

    @Column(name = "num_times_redeemed")
    private int numTimesRedeemed;

    @Column(name = "creator")
    private String creator;

    @Column(name = "active")
    private Boolean active;


    public Reward(String name, String description, int cost, Long businessID) {
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.businessID = businessID;
    }
}
