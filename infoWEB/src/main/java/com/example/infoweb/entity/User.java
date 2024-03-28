/*
 *   DTO로 전달받은 값을 엔티티로 변환하는 클래스
 *   작성자: 이준영
 *
 * */

package com.example.infoweb.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String userid;
    @Column
    private String password;

    public User() {}

    public User(Long id, String userid, String password) {
        this.id = id;
        this.userid = userid;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userid='" + userid + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
