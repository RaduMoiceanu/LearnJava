package com.icode.bookmarks;

import com.icode.bookmarks.filter.ValidateUserInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by Radu on 1/8/2017.
 */
@Configuration
public class BookmarksApplicationConfig extends WebMvcConfigurerAdapter {

    @Bean
    ValidateUserInterceptor getValidateUserInterceptor() {
        return new ValidateUserInterceptor();
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);

        registry.addInterceptor(getValidateUserInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/resources/**", "/login", "/register");
    }
}
