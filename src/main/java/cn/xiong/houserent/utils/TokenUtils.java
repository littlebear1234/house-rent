package cn.xiong.houserent.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.xiong.houserent.entity.User;
import cn.xiong.houserent.service.UserService;
import cn.xiong.houserent.service.impl.UserServiceImpl;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Date;

/**
 * @description 生成token工具类
 */
public class TokenUtils {

        private static UserServiceImpl staticUserService;

        @Autowired
        private  UserServiceImpl userService;

    @PostConstruct
    public void setUserService(){
        staticUserService = userService;
    }

    //生成token
    public static String genToken(String userId, String sign) {
        return JWT.create().withAudience(userId) // 将 user id 保存到 token 里面,作为载荷
                .withExpiresAt(DateUtil.offsetHour(new Date(), 2)) // 2小时后token过期
                .sign(Algorithm.HMAC256(sign)); // 以 password 作为 token 的密钥
    }

    //获取当前用户对象信息
    public static User getCurrentUser(){
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

            String token = request.getHeader("token");
            if (StrUtil.isNotBlank(token)){

                String userId = JWT.decode(token).getAudience().get(0);
                return staticUserService.getById(Integer.valueOf(userId));
            }
        } catch (Exception e) {
            return null;
        }

        return null;

    }

}
