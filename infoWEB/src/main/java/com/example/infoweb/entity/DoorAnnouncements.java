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
    private Map<String, Object> door_announcement_info;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId                     // UserInfo의 id를 DoorAnnouncements의 id로 매핑합니다.
    @JoinColumn(name = "id")    // UserInfo와 동일한 id를 사용합니다.
    private UserInfo userInfo;

}
