/*
 *   메인 페이지 컨트롤러입니다.
 *
 *   작성자: 이준영
 *
 * */

package com.example.infoweb.controller;

import com.example.infoweb.entity.*;
import com.example.infoweb.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Controller
public class MainController {

    @Autowired  // 의존성 주입
    private NaverNewsRepository naverNewsRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserInterestsRepository userInterestsRepository;
    @Autowired
    private RealTimeNewsRepository realTimeNewsRepository;
    @Autowired
    private NounFrequencyRepository nounFrequencyRepository;


    @GetMapping("/main")
    public String newMainForm(@AuthenticationPrincipal User user, @RequestParam(defaultValue = "종합") String category, Model model) {
        /*
         *   @AuthenticationPrincipal: 현재 인증된 사용자의 정보를 메서드의 파라미터로 직접 주입할 때 사용
         *   @RequestParam: HTTP 요청의 파라미터를 컨트롤러 메서드의 파라미터에 바인딩할 때 사용
         *
         * */

        // 로그인하지 않았을 때
        UserInterests userInterests = null;

        // 로그인되어 있는 유저 정보를 가져옴
        if (user != null) {
            model.addAttribute("loginInfo", user.getUsername());
            log.info("현재 로그인 정보 " + user.getUsername());

            // 로그인된 사용자의 관심분야를 가져옴
            userInterests = userInterestsRepository.findById(Objects.requireNonNull(user).getUsername()).orElse(null);
        }

        model.addAttribute("userInterests", userInterests);
        log.info("사용자 관심분야 가져오기 완료");

        Iterable<NaverNews> filteredNews;
        Iterable<NaverRealTimeNews> filteredNewsRealtime;

        // 카테고리가 종합일 경우 랜덤 뉴스 조회
        if (category.equals("종합")) {
            // 랜덤으로 메인 뉴스 가져오기
            filteredNews = naverNewsRepository.findAll()
                                              .stream()
                                              .collect(Collectors.collectingAndThen(Collectors.toList(), collected -> {
                                                  Collections.shuffle(collected);
                                                  return collected.stream();
                                              }))
                                              .limit(6)
                                              .collect(Collectors.toList());
            log.info("랜덤 메인 뉴스 가져오기 완료");

            // 랜덤으로 실시간 뉴스 가져오기
            filteredNewsRealtime = realTimeNewsRepository.findAll()
                                                         .stream()
                                                         .collect(Collectors.collectingAndThen(Collectors.toList(), collected -> {
                                                             Collections.shuffle(collected);
                                                             return collected.stream();
                                                         }))
                                                         .limit(100)
                                                         .collect(Collectors.toList());
            log.info("랜덤 실시간 뉴스 가져오기 완료");
        } else {
            // 카테고리에 따라 필터링된 메인 뉴스 가져오기
            filteredNews = naverNewsRepository.findByCategory(category)
                                              .stream()
                                              .limit(6)
                                              .collect(Collectors.toList());
            log.info("카테고리 별 메인 뉴스 가져오기 완료");

            // 카테고리에 따라 필터링된 실시간 뉴스 가져오기
            filteredNewsRealtime = realTimeNewsRepository.findByCategory(category)
                                                         .stream()
                                                         .limit(100)
                                                         .collect(Collectors.toList());
            log.info("카테고리 별 실시간 뉴스 가져오기 완료");
        }

        model.addAttribute("naverNewsList", filteredNews);
        log.info(category + "로 필터링된 메인 뉴스 데이터 조회");

        model.addAttribute("realTimeNews", filteredNewsRealtime);
        log.info(category + "로 필터링된 실시간 뉴스 데이터 조회");

        // 카테고리 선택했을 때 불 들어오게
        model.addAttribute("selectedCategory", category);

        /**
         * 키워드 및 빈도수 이미지
         * */
        // 키워드 가져오기
        Iterable<NounFrequency> nounFrequencies = StreamSupport.stream(nounFrequencyRepository.findAll().spliterator(), false)
                                                               .limit(15)
                                                               .collect(Collectors.toList());

        model.addAttribute("nounFrequencies", nounFrequencies);
        
        // 빈도수 이미지 불러오기
        //

        return "/main";
    }

    @GetMapping("/users/mypage")
    public String myPageForm(@AuthenticationPrincipal User user, Model model) {

        // 로그인되어 있는 유저 정보를 가져옴
        if (user != null) {

            // UserInfo DB 조회
            UserInfo userInfo = userRepository.findByid(user.getUsername()).orElse(null);

            model.addAttribute("loginInfo", user.getUsername());
            log.info("마이페이지 로그인 정보" + user.getUsername());

            model.addAttribute("doorLoginInfo", Objects.requireNonNull(userInfo).getDoor_id());
            log.info("마이페이지 도어 아이디 정보" + userInfo.getDoor_id());

            model.addAttribute("nameInfo", Objects.requireNonNull(userInfo).getName());
            log.info("마이페이지 이름 정보" + userInfo.getName());

            // 관심분야가 true면 체크박스 체크
            // th:checked="${userInterests.필드명}"은 UserInterests 객체의 해당 필드 값이 true인 경우 체크박스가 자동으로 선택되도록 한다
            UserInterests userInterests = userInterestsRepository.findById(user.getUsername()).orElse(new UserInterests());
            model.addAttribute("userInterests", userInterests);

        } else {
            return "/users/login";
        }

        return "/users/mypage";
    }

    @GetMapping("/investing")
    public String investingForm() {
        return "/investing";
    }

}
