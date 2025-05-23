package com.kijy.strengthhub.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long writerId;

    private String name;
    private String content;
    private String category;

    @Column(columnDefinition = "TEXT")
    private String imageUrls;

    private int likes;
    private int commentCount;

    private Date createDate;
    private Date updatedAt;
}

