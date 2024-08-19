package com.coding404.myweb.controller;

import com.coding404.myweb.command.UsersVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping("/login")
    public String login() {
        return "user/login";
    }

    //로그인 시도 메서드
    @PostMapping("/loginForm")
    public String loginForm(UsersVO vo, HttpSession session) {

        //로그인 시도

        UsersVO result = vo; //로그인 성공이라고 가정(DB)
        if(result == null) {
            //로그인 실패
            return "redirect:/user/login"; //로그인 페이지
        } else {
            session.setAttribute("userVO", result);
            return "redirect:/"; //메인 페이지
        }
    }
}
