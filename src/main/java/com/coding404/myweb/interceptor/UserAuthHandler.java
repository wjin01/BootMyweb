package com.coding404.myweb.interceptor;

import com.coding404.myweb.command.UsersVO;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserAuthHandler implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession();
        UsersVO vo = (UsersVO)session.getAttribute("userVO"); //로그인 성공시 세션값

        if(vo == null) { //세션이 없으면 로그인 페이지로
            response.sendRedirect("/user/login");
            return false;
        } else {
            return true;
        }
    }
}
