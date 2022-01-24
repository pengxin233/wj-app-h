package com.px.userService.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.px.commonUtils.R;
import com.px.commonUtils.Util;
import com.px.commonUtils.WjException;
import com.px.userService.entity.User;
import com.px.userService.entity.vo.LoginVo;
import com.px.userService.mapper.UserMapper;
import com.px.userService.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author px
 * @since 2022-01-12
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public R login(LoginVo loginVo) {
        if (loginVo == null){
            throw new WjException(400,"出现未知错误，loginVo为空");
        }

        QueryWrapper<User> wrapper = new QueryWrapper<>();

        if (StringUtils.isBlank(loginVo.getUser())){
            String msg = loginVo.getLoginType() == 0 ? "手机号不能为空" :"账号不能为空";
            throw new WjException(400,msg);
        }

        //手机号登录
        if (loginVo.getLoginType() == 0){
            wrapper.eq("phone",loginVo.getUser());
        }else{
            wrapper.eq("account",loginVo.getUser());
        }

        User user = baseMapper.selectOne(wrapper);
        boolean isRegister = false;

        if (user == null){
            //如果手机号登录，则，自动注册
            if (loginVo.getLoginType() == 0){
                user = registerUser(loginVo);
                isRegister = true;
            }else{
                throw new WjException(400,"该账号未被注册");
            }
        }

        String msg = isRegister ? "注册成功!":"登录成功，欢迎回来!";

        return R.ok().data("user",user).data("msg",msg);
    }

    private User registerUser(LoginVo loginVo){
        if (loginVo.getLoginType() == 1){
            throw new WjException(400,"出现未知错误，请通过手机号注册");
        }
        //生成账号
        String account = String.format("PX-%s",Util.getRandomStr(6));
        //生成密码
        String password = Util.getRandomStr(6);

        User user = new User();
        user.setAccount(account);
        user.setPassword(password);
        user.setPhone(loginVo.getUser());

        //存入数据库
        baseMapper.insert(user);
        return user;
    }
}
