/*
 *   도어 공지 엔티티입니다.
 *
 *   작성자: 이준영
 *
 * */

package com.example.infoweb.entity;

import com.example.infoweb.converter.JsonToMapConverter;
import jakarta.persistence.*;
import lombok.*;

import java.util.Map;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class DoorAnnouncements {

    @Id
    private String id;

    @Convert(converter = JsonToMapConverter.class)
    @Column(columnDefinition = "JSON")
    private Map<String, Object> doorAnnouncementInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private UserInfo userInfo;

}
