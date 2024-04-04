/*
 *   관심 분야 엔티티입니다.
 *
 *   작성자: 이준영
 *
 * */

package com.example.infoweb.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserInterests {

    @Id
    private String user_id;

    private boolean politics;
    private boolean economy;
    private boolean society;
    private boolean lifestyleCulture;
    private boolean it;
    private boolean world;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "user_id")
    private UserInfo userInfo;

}
