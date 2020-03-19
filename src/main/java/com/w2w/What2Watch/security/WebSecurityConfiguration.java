package com.w2w.What2Watch.security;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
@EnableOAuth2Sso
@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
/*    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .oauth2Login()
                .redirectionEndpoint()
                .baseUri("/login")
                .and()
                .loginPage("/signin")
                .and()
                .antMatcher("/**")
                .authorizeRequests()
                .anyRequest()
                .authenticated();
    }*/

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/","/images/**","/static/**",
                        "/index*", "/static/**", "/*.js", "/*.json", "/*.ico")
                .permitAll()
                .and()
                .antMatcher("/**")
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and().logout().logoutSuccessUrl("/").permitAll();
    }
}