/*
*   유저 데이터 관리를 위한 컨트롤러입니다.
*
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

@Slf4j  // 로깅 기능을 위한 어노테이션
@Controller
public class UserController {

    @Autowired  // 의존성 주입
    private UserRepository userRepository;

    @GetMapping("/users/signup")
    public String newUserForm() {
        return "/users/signup";
    }

    @GetMapping("/users/login")
    public String loginUserForm() {
        return "/users/login";
    }

    @PostMapping("/users/create")
    public String createUser(UserForm form) {

        log.info(form.toString());

        // DTO를 엔티티로 변환
        UserInfo userInfo = form.toEntity();
        log.info(userInfo.toString());

        // 중복 회원 검증
        validateDuplicateMember(userInfo);

        // 리포지토리로 엔티티를 DB에 저장
        UserInfo saved = userRepository.save(userInfo);
        log.info(saved.toString());
        
        // 성공적으로 회원가입이 끝나면 로그인 페이지로 리다이렉트
        return "redirect:/users/login";
    }

    private void validateDuplicateMember(UserInfo userInfo) {
        // 사용자 ID를 통해 이미 등록된 회원이 있는지 검사
        userRepository.findByid(userInfo.getId())
                .ifPresent(m -> {
                    // 이미 존재하는 회원이라면 예외처리
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

}
