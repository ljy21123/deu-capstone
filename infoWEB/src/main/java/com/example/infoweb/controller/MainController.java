/*
 *   메인 페이지 컨트롤러입니다.
 *
 *   작성자: 이준영
 *
 * */

package com.example.infoweb.controller;

import com.example.infoweb.embedding.Embedding;
import com.example.infoweb.entity.NaverNews;
import com.example.infoweb.entity.UserInfo;
import com.example.infoweb.entity.UserInterests;
import com.example.infoweb.repository.NaverNewsRepository;
import com.example.infoweb.repository.UserInterestsRepository;
import com.example.infoweb.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Iterator;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Controller
public class MainController {

    @Autowired  // 의존성 주입
    private NaverNewsRepository naverNewsRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserInterestsRepository userInterestsRepository;

    @GetMapping("/test")
    public String test() {
        return "/test";
    }

    @PostMapping("/search")
    public String search(@RequestParam("searchKeyword") String searchKeyword, Model model) {
        log.info("search keyword: " + searchKeyword);
        Iterable<NaverNews> items = naverNewsRepository
                .findByCategory("정치")
                .stream().limit(10)
                .collect(Collectors.toList());

        Embedding em = new Embedding();
        Iterator<NaverNews> iterator = items.iterator();
        NaverNews news = iterator.next();
        NaverNews news2 = iterator.next();

        double [] search = em.getEmbedding(searchKeyword);
        log.info("검색어{}", search);


        // 값이 높을수록 유사
        // 먼저 like 연산으로 제목을 검색 후 개수가 부족하면 임베딩 검색을 수행한다....
        double result = em.cosineDistance(search, news.getEmbedding());
        double result2 = em.cosineDistance(search, news2.getEmbedding());
        log.info("검색어와 1번항목: {}", result);
        log.info("검색어와 2번항목: {}", result2);
        log.info("{} {}",news.getTitle(), news.getEmbedding());
        log.info("{} {}",news2.getTitle(), news2.getEmbedding());
        return "redirect:/test";
    }

    @GetMapping("/main")
    public String newMainForm(@AuthenticationPrincipal User user, @RequestParam(defaultValue = "정치") String category, Model model) {
        /*
        *   @AuthenticationPrincipal: 현재 인증된 사용자의 정보를 메서드의 파라미터로 직접 주입할 때 사용
        *   @RequestParam: HTTP 요청의 파라미터를 컨트롤러 메서드의 파라미터에 바인딩할 때 사용
        *
        * */

        // 로그인 하지 않았을 때
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

        // 카테고리에 따라 필터링된 뉴스 가져오기
        Iterable<NaverNews> filteredNews = naverNewsRepository.findByCategory(category)
                                                              .stream()
                                                              .limit(10)
                                                              .collect(Collectors.toList());
        model.addAttribute("naverNewsList", filteredNews);
        log.info(category + "로 필터링 된 뉴스 데이터 조회");

        // 카테고리 선택했을때 불 들어오게
        model.addAttribute("selectedCategory", category);

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

}
