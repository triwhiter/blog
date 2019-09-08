package com.xz.blog.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * @Author: xuzhen
 * @Description:
 * @Date: Created in 0:54 2019/8/25
 * @Modified By:
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    // 这个方法是用来配置静态资源的，比如html，js，css，等等
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");

    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/admin/**")
                .addPathPatterns("/admin")
                .excludePathPatterns("/admin/login","/admin/login.html","/admin/dist/**","/admin/plugins/**");
    }
}
