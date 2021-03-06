package org.zzq.oauthserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 登陆模块
 */
@Controller
public class LoginController {

    @GetMapping(value = "/login")
    public String login(){
        return "login";
    }

    @GetMapping(value = "/login-error")
    public String loginError(Model model){
        model.addAttribute("loginError",true);
        return "login";
    }
}
