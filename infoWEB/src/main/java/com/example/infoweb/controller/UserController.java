/*
*   유저 데이터 관리를 위한 컨트롤러
*   작성자: 이준영
*
* */

package com.example.infoweb.controller;

import com.example.infoweb.dto.UserForm;
import com.example.infoweb.entity.UserInfo;
import com.example.infoweb.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users/signup_test")
    public String newUserForm() {
        return "/users/signup_test";
    }

    @PostMapping("/users/create")
    public String createUser(UserForm form) {

        log.info(form.toString());

        // DTO를 엔티티로 변환
        UserInfo userInfo = form.toEntity();
        log.info(userInfo.toString());

        // 리포지토리로 엔티티를 DB에 저장
        UserInfo saved = userRepository.save(userInfo);
        log.info(saved.toString());

        return "";
    }

}
