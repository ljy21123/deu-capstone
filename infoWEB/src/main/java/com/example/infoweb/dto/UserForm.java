/*
 *   회원가입 폼 데이터를 전달받는 DTO 입니다.
 *
 *   작성자: 이준영
 *
 * */

package com.example.infoweb.dto;

import com.example.infoweb.entity.UserInfo;
import lombok.AllArgsConstructor;
import lombok.ToString;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@ToString           // 데이터를 잘 받았는지 확인할 toString() 자동화
@AllArgsConstructor // 전송받은 제목과 내용을 필드에 저장하는 생성자 자동화
public class UserForm {

    private String id;
    private String pw;
    private String door_id;
    private String door_pw;
    private Boolean door_alert;
    private String name;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserInfo toEntity() {
        return new UserInfo(id, passwordEncoder.encode(pw), door_id, door_pw, false, name);
    }

}
