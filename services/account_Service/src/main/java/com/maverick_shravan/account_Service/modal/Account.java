package com.maverick_shravan.account_Service.modal;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Data
@Entity
@Table(name = "Account", schema = "maveric_account_db")
public class Account {
    @Id
    @Column(name = "id")
    @NotBlank(message = "ID cannot be blank")
    private String id;

    @Column(name = "type")
    @NotBlank(message = "Type cannot be blank")
    private String type;

    @Column(name = "customerId")
    @NotBlank(message = "Customer ID cannot be blank")
    private String customerId;


}
