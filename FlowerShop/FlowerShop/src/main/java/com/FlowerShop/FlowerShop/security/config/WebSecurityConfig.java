package com.FlowerShop.FlowerShop.security.config;

import com.FlowerShop.FlowerShop.appuser.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configurable
@AllArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig  extends WebSecurityConfigurerAdapter {


    private final AppUserService appUserService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final LoginSuccessHandler loginSuccessHandler;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/loginpage","/registers", "/logout",
                        "/assets/**", "/static/**","/flowers/**","/","/Flower","/FlowerDetail","/Contact")
                .permitAll()
                .antMatchers("/admin/**","/flower/**","/category/**","/supplier/**","/receipt/**").hasAnyAuthority("ADMIN")
                .antMatchers("/user/**").hasAnyAuthority("USER")
                .antMatchers("/api/v*/registration/**")
                .permitAll()
                .anyRequest().authenticated().and()
                //form login
                .formLogin()
                .loginPage("/loginpage").successHandler(loginSuccessHandler)
                .failureUrl("/loginpage?error=true")
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("http://localhost:8080").and()
                .exceptionHandling()
                .accessDeniedPage("/access-denied");
//                .permitAll();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        provider.setUserDetailsService(appUserService);
        return provider;
    }
}
