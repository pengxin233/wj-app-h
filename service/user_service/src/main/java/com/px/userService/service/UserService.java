package com.px.userService.service;

import com.px.commonUtils.R;
import com.px.userService.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.px.userService.entity.vo.LoginVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author px
 * @since 2022-01-12
 */
public interface UserService extends IService<User> {

    /**
     * 登录
     * @param loginVo
     */
    R login(LoginVo loginVo);
}
