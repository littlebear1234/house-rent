package cn.xiong.houserent.controller;


import cn.hutool.core.util.StrUtil;
import cn.xiong.houserent.common.R;
import cn.xiong.houserent.entity.User;
import cn.xiong.houserent.entity.dto.LoginUser;
import cn.xiong.houserent.service.UserService;
import cn.xiong.houserent.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户登录
     */
    @PostMapping("/login")
    public R  login( @RequestBody  LoginUser loginUser) throws NoSuchAlgorithmException {
        String username = loginUser.getUsername();
        String password = loginUser.getPassword();
        if (StrUtil.isBlank(username) || StrUtil.isBlank(password)){
            return R.error("用户名或密码不能为空");
        }
        LoginUser user = userService.login(loginUser);

        Map<String, String> payload = new HashMap<>();
        payload.put("name", user.getUsername());
        payload.put("password", user.getPassword());

        // 生成token
        String token = JWTUtils.getToken(payload);
        user.setToken(token);
        return R.ok(user);
    }

    @PostMapping("/register")
    public R register(@RequestBody User user) throws NoSuchAlgorithmException {
        String username = user.getUsername();
        String password = user.getPassword();
        if (StrUtil.isBlank(username) || StrUtil.isBlank(password)){
            return R.error("用户名或密码不能为空");
        }
        if (userService.register(user)){
            return R.ok("注册成功");
        }
        return R.error("注册失败");
    }
}
