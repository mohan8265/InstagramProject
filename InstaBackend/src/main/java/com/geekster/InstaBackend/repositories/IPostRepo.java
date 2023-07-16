package com.geekster.InstaBackend.repositories;

import com.geekster.InstaBackend.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPostRepo extends JpaRepository<Post,Integer> {
}
