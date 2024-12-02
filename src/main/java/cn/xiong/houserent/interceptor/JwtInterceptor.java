package cn.xiong.houserent.interceptor;

import cn.xiong.houserent.utils.JWTUtils;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.HashMap;

public class JwtInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HashMap<String, String> map=new HashMap<>();
        //从http请求头获取token
        String token = request.getHeader("token");
        try {
            //如果验证成功放行请求
            DecodedJWT verify = JWTUtils.verify(token);
            return true;
        }
        catch (Exception exception)
        {
            map.put("msg","验证失败："+exception);
        }
        String json = map.toString();
        response.setContentType("application/json:charset=UTF=8");
        response.getWriter().println(json);
        return false;
    }
}
