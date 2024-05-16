/*
*   유저 데이터 관리를 위한 컨트롤러입니다.
*
*   작성자: 이준영
*
* */

package com.example.infoweb.controller;

import com.example.infoweb.dto.UserForm;
import com.example.infoweb.entity.UserInfo;
import com.example.infoweb.entity.UserInterests;
import com.example.infoweb.repository.UserInterestsRepository;
import com.example.infoweb.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Objects;

@Slf4j  // 로깅 기능을 위한 어노테이션
@Controller
public class UserController {

    @Autowired  // 의존성 주입
    private UserRepository userRepository;
    @Autowired
    private UserInterestsRepository userInterestsRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @GetMapping("/users/signup")
    public String newUserForm() {
        return "/users/SignUp";
    }

    @GetMapping("/users/login")
    public String loginUserForm(@AuthenticationPrincipal User user) {

        if (user != null) {
            return "/main";
        }

        return "/users/SignIn";
    }

    @PostMapping("/users/create")
    public String createUser(UserForm form, Model model) {

        log.info("회원정보 생성 요청");

        // pw와 cpw 비교 (비밀번호 확인)
        if (!Objects.equals(form.getCpw(), form.getPw())) {
            log.info("회원가입 - 비밀번호 confirm 확인");
            model.addAttribute("passwordError", "비밀번호가 일치하지 않습니다.");
            return "/users/SignUp";
        }

        // DTO를 엔티티로 변환
        UserInfo userEntity = form.toEntity();
        log.info("UserInfo 엔티티 변환 완료");

        // 중복 회원 검증
        validateDuplicateMember(userEntity);

        // 리포지토리로 엔티티를 DB에 저장
        UserInfo saved = userRepository.save(userEntity);
        log.info("UserInfo DB 저장 완료");

        // UserInterests 테이블에도 id 저장
        UserInterests userInterests = new UserInterests();
        userInterests.setUserInfo(saved);               // 연관 관계 설정 (UserInterests의 user_id 필드가 UserInfo의 id 필드와 동일한 값을 가지게 함)

        userInterests.setPolitics(true);                // 회원가입시 모든 분야 true
        userInterests.setEconomy(true);
        userInterests.setSociety(true);
        userInterests.setLifestyleCulture(true);
        userInterests.setIt(true);
        userInterests.setWorld(true);
        userInterests.setStock(true);

        userInterestsRepository.save(userInterests);    // DB에 저장
        log.info("회원가입시 관심분야 DB 저장 완료");
        
        // 성공적으로 회원가입이 끝나면 로그인 페이지로 리다이렉트
        return "redirect:/users/login";
    }

    @PostMapping("/users/update")
    public String updateUser(@AuthenticationPrincipal User user, UserForm form, Model model) {

        log.info("비밀번호 수정 요청");

        // pw와 cpw 비교 (비밀번호 확인)
//        if (!Objects.equals(Objects.requireNonNull(userEntity).getPw(), form.getPw()) ||
//                !Objects.equals(form.getPw(), form.getCpw())) {
//            log.info("마이페이지 - 비밀번호 confirm 확인");
//            // model.addAttribute("passwordError", "비밀번호가 일치하지 않습니다.");
//            return "redirect:/users/mypage";
//        }

        // 로그인 정보 가져오기
        UserInfo userEntity = userRepository.findByid(user.getUsername()).orElse(null);
        UserInterests userInterests = userInterestsRepository.findById(user.getUsername()).orElse(new UserInterests());

        // 정보 업데이트
        if (userEntity != null) {

            // 빈 칸일시 비밀번호 저장 안함
            if (form.getPw() != null && !form.getPw().isEmpty()) {
                userEntity.setPw(passwordEncoder.encode(form.getPw()));
                // userEntity.setDoor_pw(form.getDoor_pw());
            }

            // 체크박스의 체크 유무에 따라 DB에 저장
            userInterests.setPolitics(form.getPolitics() != null);
            userInterests.setEconomy(form.getEconomy() != null);
            userInterests.setSociety(form.getSociety() != null);
            userInterests.setLifestyleCulture(form.getLifestyleCulture() != null);
            userInterests.setIt(form.getIt() != null);
            userInterests.setWorld(form.getWorld() != null);
            userInterests.setStock(form.getStock() != null);

            // 업데이트 된 엔티티 저장
            userInterestsRepository.save(userInterests);
            log.info("관심분야 업데이트 DB에 저장 완료");

            userRepository.save(userEntity);
            log.info("비밀번호 업데이트 DB에 저장 완료");

        }

        return "redirect:/main";

    }

    @PostMapping("/users/delete")
    public String deleteUser(@AuthenticationPrincipal User user) {

        log.info("회원정보 삭제 요청");

        // 삭제 대상 가져오기
        UserInfo userEntity = userRepository.findByid(user.getUsername()).orElse(null);
        log.info("UserInfo 삭제 대상 가져오기 완료");
        UserInterests userInterests = userInterestsRepository.findById(user.getUsername()).orElse(null);
        log.info("UserInterests 삭제 대상 가져오기 완료");
        
        // 대상 엔티티 삭제
        userInterestsRepository.delete(userInterests);
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
