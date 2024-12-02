package cn.xiong.houserent.service;

import cn.xiong.houserent.entity.User;
import cn.xiong.houserent.entity.dto.LoginUser;
import com.baomidou.mybatisplus.extension.service.IService;

import java.security.NoSuchAlgorithmException;

/**
* @author xiong
* @description 针对表【users(存储前台用户和后台管理员的信息)】的数据库操作Service
* @createDate 2024-12-03 00:44:35
*/
public interface UserService extends IService<User> {

    LoginUser login(LoginUser loginUser) throws NoSuchAlgorithmException;

    boolean register(User user);

}
