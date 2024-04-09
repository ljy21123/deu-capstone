/*
 *   메인 페이지 컨트롤러입니다.
 *
 *   작성자: 이준영
 *
 * */

package com.example.infoweb.controller;

import com.example.infoweb.entity.NaverNews;
import com.example.infoweb.repository.NaverNewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Controller
public class MainController {

    @Autowired  // 의존성 주입
    private NaverNewsRepository naverNewsRepository;

    @GetMapping("/main")
    public String newMainForm(@AuthenticationPrincipal User user, @RequestParam(defaultValue = "정치") String category, Model model) {
        /*
        *   @AuthenticationPrincipal: 현재 인증된 사용자의 정보를 메서드의 파라미터로 직접 주입할 때 사용
        *   @RequestParam: HTTP 요청의 파라미터를 컨트롤러 메서드의 파라미터에 바인딩할 때 사용
        *
        * */

        // 로그인되어 있는 유저 정보를 가져옴
        if (user != null) {
            model.addAttribute("loginInfo", user.getUsername());
        }

        // 카테고리에 따라 필터링된 뉴스 가져오기
        Iterable<NaverNews> filteredNews = naverNewsRepository.findByCategory(category)
                                                              .stream()
                                                              .limit(10)
                                                              .collect(Collectors.toList());
        model.addAttribute("naverNewsList", filteredNews);

        // 카테고리 선택했을때 불 들어오게
        model.addAttribute("selectedCategory", category);

        return "/main";
    }

}
