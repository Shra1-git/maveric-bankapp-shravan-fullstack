package com.maverick_shravan.transaction_Service.modal;



import java.util.Date;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Data
@Entity
@Table(name = "Transaction")
public class Transaction {
    @Id
    @Column(name = "id")
    private String id;

    @NotBlank(message = "Type cannot be blank")
    @Column(name = "type")
    private String type;

    @NotNull(message = "Amount must be provided")
    @Column(name = "amount")
    private Double amount;

    @NotBlank(message = "Account ID cannot be blank")
    @Column(name = "accountId")
    private String accountId;

    @NotNull(message = "Created at date must be provided")
    @Column(name = "createdAt")
    private Date createdAt;

}