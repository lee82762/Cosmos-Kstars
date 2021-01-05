package com.example.restfulwebservice.config;

//spring security를 사용하는 config

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration //메모리의 설정정보를 같이 로딩한다.
public class SecuritySwagger extends WebSecurityConfigurerAdapter {

    @Autowired//bean 자동주입
    public void configeGrobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("jaebum")
                .password("{noop}test123")//noop 인코딩없이 바로 사용가능하게 해주는 operation 나중에는 인코딩 알고리즘 사용
                .roles("USER");
    }

    //postmapping을 활성화하기 위해 security 접근을 원활하게 해줌
    //이 메소드가 없으면 post할때 403forbiden error발생
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().httpBasic().and().authorizeRequests().anyRequest().authenticated();
    }
}
