/*
 *   폼 데이터를 전달받는 DTO
 *   작성자: 이준영
 *
 * */

package com.example.infoweb.dto;

import com.example.infoweb.entity.User;

public class UserForm {

    private String userid;
    private String password;

    // 전송받은 제목과 내용을 필드에 저장하는 생성자
    public UserForm(String userid, String password) {
        this.userid = userid;
        this.password = password;
    }

    // 데이터를 잘 받았는지 확인할 toString() 메서드
    @Override
    public String toString() {
        return "UserForm{" +
                "userid='" + userid + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public User toEntity() {
        return new User(null, userid, password);
    }

}
