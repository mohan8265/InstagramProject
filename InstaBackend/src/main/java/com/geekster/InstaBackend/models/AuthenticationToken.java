package com.geekster.InstaBackend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AuthenticationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tokenId;
    private String tokenName;
    private LocalDate tokenCreationDate;

    @OneToOne
    @JoinColumn(name = "fk_user_id")
    private User user;

    public AuthenticationToken(User user){
        this.user = user;
        this.tokenName = UUID.randomUUID().toString();
        this.tokenCreationDate = LocalDate.now();
    }
}
