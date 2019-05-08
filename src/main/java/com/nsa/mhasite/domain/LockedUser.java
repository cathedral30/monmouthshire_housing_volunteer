package com.nsa.mhasite.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
public class LockedUser {
    private String username;
    private Integer attempts;
    private Boolean locked;
    private Timestamp locked_until;
}
