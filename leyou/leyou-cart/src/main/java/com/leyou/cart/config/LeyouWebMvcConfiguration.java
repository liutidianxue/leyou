package com.leyou.cart.config;

import com.leyou.cart.interceptor.LoginInterCeptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author hewei
 * @date 2019/9/21 - 0:27
 */
@Configuration
public class LeyouWebMvcConfiguration implements WebMvcConfigurer {
    @Autowired
    private LoginInterCeptor loginInterCeptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterCeptor).addPathPatterns("/**");
    }
}
