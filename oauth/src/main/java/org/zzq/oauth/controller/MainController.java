package org.zzq.oauth.controller;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController {

    @RequestMapping(value = "/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/login-error")
    public String loginError(HttpServletRequest request, Model model) {
        model.addAttribute( "loginError"  , true);

        AuthenticationException exception =
                (AuthenticationException)request.getSession().getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
        System.out.println("error = " + exception.toString() );
        return "login";
    }

    @RequestMapping(value = "/home")
    public String home(){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println("当前登陆用户：" + name);
        return "home";
    }


}
