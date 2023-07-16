package com.geekster.InstaBackend.repositories;

import com.geekster.InstaBackend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepo extends JpaRepository<User,Integer> {
    User findByUserEmail(String currentEmail);
}
