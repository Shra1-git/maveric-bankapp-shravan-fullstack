package com.maverick_shravan.User_Service.modal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
@Data
@Entity
@Table(name = "User")
public class User {
    @Id
    @Column(name = "id")
    private String id;

    @NotBlank(message = "First name cannot be blank")
    @Column(name = "firstName")
    private String firstName;

    @Column(name = "middleName")
    private String middleName;

    @NotBlank(message = "Last name cannot be blank")
    @Column(name = "lastName")
    private String lastName;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Invalid email format")
    @Column(name = "email", unique = true)
    private String email;

    @NotBlank(message = "Phone number cannot be blank")
    @Size(min = 10, max = 15, message = "Phone number must be between 10 and 15 characters")
    @Column(name = "phoneNumber")
    private String phoneNumber;

    @NotBlank(message = "Address cannot be blank")
    @Column(name = "address")
    private String address;

    @NotNull(message = "Date of birth must be provided")
    @Column(name = "dateOfBirth")
    private Date dateOfBirth;


    @Column(name = "createdAt")
    private Date createdAt;


    @Column(name = "updatedAt")
    private Date updatedAt;

    @NotBlank(message = "Password cannot be blank")
    @Column(name = "password")
    private String password;

    @NotBlank(message = "Role cannot be blank")
    @Column(name = "role")
    private String role;

}
