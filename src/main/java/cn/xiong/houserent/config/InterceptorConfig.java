package cn.xiong.houserent.config;

import cn.xiong.houserent.interceptor.JwtInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器配置
 */
@Configuration
public class InterceptorConfig  implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //添加拦截器
        registry.addInterceptor(new JwtInterceptor())
                //拦截的路径 需要进行token验证的路径
                .addPathPatterns("/**")
                //放行的路径
                .excludePathPatterns("/user/login","/user/register");
    }


}
