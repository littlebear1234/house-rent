package cn.xiong.houserent.entity.dto;

import lombok.Data;

@Data
public class LoginUser {
    // 用户名
    private String username;
    // 密码
    private String password;
    // token
    private String token;
}
