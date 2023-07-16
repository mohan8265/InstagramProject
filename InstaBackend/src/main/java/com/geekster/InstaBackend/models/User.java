package com.geekster.InstaBackend.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @NotBlank(message = "first name can't be blank")
    private String userFirstName;

    @NotBlank(message = "last name can't be blank")
    private String userLastName;

    private Integer userAge;

    @Email(message = "enter a valid email")
    @Column(unique = true)
    private String userEmail;

    private String userPassword;

    @Column(unique = true)
    private String userPhoneNumber;
}
