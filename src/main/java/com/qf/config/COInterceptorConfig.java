package com.qf.config;


import com.qf.interceptor.COInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class COInterceptorConfig implements WebMvcConfigurer {



    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //设置拦截器并指定拦截路径
        //registry.addInterceptor(new TokenInterceptor(jedisCore)).addPathPatterns("/api/**");
        registry.addInterceptor(new COInterceptor()).addPathPatterns("/**");
        //registry.addInterceptor(new TokenInterceptor(jedisCore)).addPathPatterns("/**");//拦截所有
        //registry.addInterceptor(new TokenInterceptor(jedisCore)).addPathPatterns("/**").excludePathPatterns("/test");//指定不拦截
        //添加自定义拦截器
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
