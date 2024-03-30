/*
 *   DTO로 전달받은 값을 엔티티로 변환하는 클래스
 *   작성자: 이준영
 *
 * */

package com.example.infoweb.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString           // 데이터를 잘 받았는지 확인할 toString() 자동화
@AllArgsConstructor // 전송받은 제목과 내용을 필드에 저장하는 생성자 자동화
@NoArgsConstructor
@Entity
public class UserInfo {

    @Id
    @Column(nullable = false, length = 20)
    private String id;

    @Column(nullable = false, length = 20)
    private String pw;

    @Column(length = 8)
    private String door_id;

    @Column(length = 20)
    private String door_pw;

    @Column
    private Boolean door_alert = false;

    @Column(length = 10)
    private String name;

}
