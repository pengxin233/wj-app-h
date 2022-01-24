package com.px.userService.controller;


import com.px.commonUtils.R;
import com.px.userService.entity.vo.LoginVo;
import com.px.userService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author px
 * @since 2022-01-12
 */
@RestController
@CrossOrigin
@RequestMapping("/userService/user")
public class UserController{
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public R login(@RequestBody LoginVo loginVo){
        System.out.println(loginVo);
        return userService.login(loginVo);
    }
}

