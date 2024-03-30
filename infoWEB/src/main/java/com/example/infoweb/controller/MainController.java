/*
 *   임시 메인 페이지 컨트롤러
 *   작성자: 이준영
 *
 * */

package com.example.infoweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/main")
    public String newMainForm() {
        return "/main";
    }

}
