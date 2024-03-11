package com.maverick_shravan.balance_Service.modal;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

@Data
@Document(collection = "Balance")
public class Balance {

    @Id
    private String id;

    @NotBlank(message = "Account ID cannot be blank")
    private String accountId;

    @Min(value = 0, message = "Amount must be greater than 0")
    private double amount;

    @NotBlank(message = "Currency cannot be blank")
    private String currency;

    @NotNull(message = "Created at date must be provided")
    private Date createdAt;

    @NotNull(message = "Updated at date must be provided")
    private Date updatedAt;


}