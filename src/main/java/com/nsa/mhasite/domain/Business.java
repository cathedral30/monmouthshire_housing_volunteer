package com.nsa.mhasite.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "business")
public class Business {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "business_name")
    private String Bname;

    @Column(name = "email_address")
    private String BusinessEmail;

    public Business(String bname, String businessEmail) {
        Bname = bname;
        BusinessEmail = businessEmail;
    }
}
