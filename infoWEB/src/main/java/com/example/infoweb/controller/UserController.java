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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Objects;

@Slf4j  // 로깅 기능을 위한 어노테이션
@Controller
public class UserController {

    @Autowired  // 의존성 주입
    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // 템플릿 테스트
    @GetMapping("/index")
    public String testForm() {
        return "/blog/index";
    }

    @GetMapping("/users/signup")
    public String newUserForm() {
        return "/users/signup";
    }

    @GetMapping("/users/login")
    public String loginUserForm(@AuthenticationPrincipal User user) {

        if (user != null) {
            return "/main";
        }

        return "/users/login";
    }

    @PostMapping("/users/create")
    public String createUser(UserForm form) {

        log.info("회원정보 생성 요청");

        // DTO를 엔티티로 변환
        UserInfo userEntity = form.toEntity();
        log.info(userEntity.toString());

        // 중복 회원 검증
        validateDuplicateMember(userEntity);

        // 리포지토리로 엔티티를 DB에 저장
        UserInfo saved = userRepository.save(userEntity);
        log.info(saved.toString());
        
        // 성공적으로 회원가입이 끝나면 로그인 페이지로 리다이렉트
        return "redirect:/users/login";
    }

    @PostMapping("/users/update")
    public String updateUser(@AuthenticationPrincipal User user, UserForm form) {

        log.info("비밀번호 수정 요청");

        // 로그인 정보 가져오기
        UserInfo userEntity = userRepository.findByid(user.getUsername()).orElse(null);

        // 정보 업데이트
        if (userEntity != null) {

            if (form.getPw() != null && !form.getPw().isEmpty()) {
                userEntity.setPw(passwordEncoder.encode(form.getPw()));
                userEntity.setDoor_pw(form.getDoor_pw());
            }

            // 업데이트 된 엔티티 저장
            UserInfo saved = userRepository.save(userEntity);
            log.info(saved.toString());
        }

        return "redirect:/main";

    }

    @PostMapping("/users/delete")
    public String deleteUser(@AuthenticationPrincipal User user) {

        log.info("회원정보 삭제 요청");

        // 삭제 대상 가져오기
        UserInfo userEntity = userRepository.findByid(user.getUsername()).orElse(null);
        log.info(Objects.requireNonNull(userEntity).toString());
        
        // 대상 엔티티 삭제
        userRepository.delete(userEntity);

        return "redirect:/logout";
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
