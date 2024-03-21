/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deu.se.ood;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author USER
 */

@Controller
public class SystemController {

    @GetMapping("/")
    public String index() {
        return "project_list";  // project_list.jsp 를 읽어서 반환
    }
}
