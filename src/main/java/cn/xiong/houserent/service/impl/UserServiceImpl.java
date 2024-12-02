package cn.xiong.houserent.service.impl;

import cn.xiong.houserent.common.Constants;
import cn.xiong.houserent.common.ServiceException;
import cn.xiong.houserent.entity.dto.LoginUser;
import cn.xiong.houserent.utils.EncryptUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.xiong.houserent.entity.User;
import cn.xiong.houserent.service.UserService;
import cn.xiong.houserent.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.security.NoSuchAlgorithmException;

/**
* @author xiong
* @description 针对表【users(存储前台用户和后台管理员的信息)】的数据库操作Service实现
* @createDate 2024-12-03 00:44:35
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public LoginUser login(LoginUser loginUser) throws NoSuchAlgorithmException {
        String username = loginUser.getUsername();
        String password = loginUser.getPassword();
        User user = userMapper.selectByUsername(username);
        if (user == null){
            throw new ServiceException(Constants.CODE_500, "用户不存在，请重新登录");
        }
        if (!user.getPassword().equals(EncryptUtils.md5(password))){
            throw new ServiceException(Constants.CODE_500, "密码错误，请重新登录");
        }
        return loginUser;
    }

    @Override
    public boolean register(User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        User user1 = userMapper.selectByUsername(username);
        if (user1 != null){
            throw new ServiceException(Constants.CODE_500, "用户已存在");
        }
        try {
            user.setPassword(EncryptUtils.md5(password));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return userMapper.insert(user) > 0;
    }
}




