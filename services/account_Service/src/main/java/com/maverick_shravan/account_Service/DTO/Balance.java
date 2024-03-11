package com.maverick_shravan.account_Service.DTO;

import java.util.Date;

import lombok.Data;

import java.util.Date;

@Data
public class Balance {
    private String id;
    private String accountId;
    private double amount;
    private String currency;
    private Date createdAt;
    private Date updatedAt;
}
