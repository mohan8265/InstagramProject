package com.geekster.InstaBackend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;
    private Timestamp postCreatedDate;
    private Timestamp postUpdatedDate;
    private String postData;

    @ManyToOne
    @JoinColumn(name = "fk_user_id")
    private User user;
}
