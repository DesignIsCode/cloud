package org.zzq.oauth.controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zzq.entity.User;
import org.zzq.oauth.core.MainThreadPool;
import org.zzq.oauth.core.UserCallable;
import org.zzq.oauth.service.UserService;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value="/create",method = RequestMethod.POST)
    @ResponseBody
    public String createUser(@Param("username")String username,@Param("password")String password,
                             @Param("remark")String remark){

        //利用线程池
        UserCallable userCallable = new UserCallable(username,password,remark,userService);
        Future<User> future = MainThreadPool.executor.submit(userCallable);
//        userService.createUser(username,password,remark);
        try {
            User user = future.get(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            System.out.println("超时了");
            e.printStackTrace();
        }

        return "";
    }

}
