package org.zzq.oauth.controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zzq.entity.User;
import org.zzq.oauth.service.UserService;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value="/create",method = RequestMethod.POST)
    @ResponseBody
    public String createUser(@Param("username")String username,@Param("password")String password,
                             @Param("remark")String remark){
        userService.createUser(username,password,remark);
        return "";
    }

}
