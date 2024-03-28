/*
*   유저 데이터 관리를 위한 컨트롤러
*   작성자: 이준영
*
* */

package com.example.infoweb.controller;

import com.example.infoweb.dto.UserForm;
import com.example.infoweb.entity.User;
import com.example.infoweb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users/signupTest")
    public String newUserForm() {
        return "/users/signupTest";
    }

    @PostMapping("/users/create")
    public String createUser(UserForm form) {

        System.out.println(form.toString());

        // DTO를 엔티티로 변환
        User user = form.toEntity();
        System.out.println(user.toString());

        // 리파지터리로 엔티티를 DB에 저장
        User saved = userRepository.save(user);
        System.out.println(saved.toString());

        return "";
    }

}
