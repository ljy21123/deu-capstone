/*
 *   네이버 뉴스 엔티티입니다.
 *
 *   작성자: 이준영
 *
 * */

package com.example.infoweb.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class NaverNews {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String newsUrl;

    private String title;

    @Lob
    private String summary;

    @Lob
    private String original;

    private String imageUrl;

    private String publisher;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String category;

}
