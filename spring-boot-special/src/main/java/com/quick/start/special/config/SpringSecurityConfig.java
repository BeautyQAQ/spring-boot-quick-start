package com.quick.start.special.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) throws Exception {
        // 放行静态资源
        web.ignoring()
                .antMatchers(HttpMethod.GET,
                        "/swagger-resources/**",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js",
                        "/swagger-ui.html",
                        "/webjars/**",
                        "/v2/**",
                        "/doc.html");
    }


    /**
     * anyRequest | 匹配所有请求路径
     * access | SpringEl表达式结果为true时可以访问
     * anonymous | 匿名可以访问
     * denyAll | 用户不能访问
     * fullyAuthenticated | 用户完全认证可以访问（非remember-me下自动登录）
     * hasAnyAuthority | 如果有参数，参数表示权限，则其中任何一个权限可以访问
     * hasAnyRole | 如果有参数，参数表示角色，则其中任何一个角色可以访问
     * hasAuthority | 如果有参数，参数表示权限，则其权限可以访问
     * hasIpAddress | 如果有参数，参数表示IP地址，如果用户IP和参数匹配，则可以访问
     * hasRole | 如果有参数，参数表示角色，则其角色可以访问
     * permitAll | 用户可以任意访问
     * rememberMe | 允许通过remember-me登录的用户访问
     * authenticated | 用户登录后可访问
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 关闭csrf
        http.csrf().disable()
                // .sessionManagement()// 基于token，所以不需要session
                // .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                // .and()
                .authorizeRequests()
                // 任何人都能访问这个请求
                .antMatchers("/captcha").permitAll()
                .antMatchers("/swagger-ui/**").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                .antMatchers("/v3/**").permitAll()
                .antMatchers("/**").permitAll()

                .anyRequest().authenticated()
                .and()
                .formLogin()
                // 登录页面 不设限访问
                .loginPage("/login.html")
                // 拦截的请求
                .loginProcessingUrl("/login")
                .permitAll()
                .and()
                .rememberMe().rememberMeParameter("rememberme")
                // 防止iframe 造成跨域
                .and()
                .headers()
                .frameOptions()
                .disable()
                .and();

        // 禁用缓存
        http.headers().cacheControl();

    }

}
