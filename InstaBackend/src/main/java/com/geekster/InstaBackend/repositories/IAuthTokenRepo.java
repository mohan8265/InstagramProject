package com.geekster.InstaBackend.repositories;

import com.geekster.InstaBackend.models.AuthenticationToken;
import com.geekster.InstaBackend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAuthTokenRepo extends JpaRepository<AuthenticationToken,Integer> {
    AuthenticationToken findByUser(User existingUser);

    AuthenticationToken findByTokenName(String tokenName);
}
