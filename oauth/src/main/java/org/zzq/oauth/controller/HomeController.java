package org.zzq.oauth.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @RequestMapping("/admin")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String printAdmin() {
        return "如果你看见这句话，说明你有ADMIN角色";
    }

    @RequestMapping("/user")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_USER')")
    public String printUser() {
        return "如果你看见这句话，说明你有USER角色";
    }

    @RequestMapping("/oauth/r")
    @ResponseBody
    @PreAuthorize("hasPermission('/oauth','r')")
    public String printOauthR(){
        return "如果你看见这句话，说明你访问/oauth路径具有r权限";
    }

}
