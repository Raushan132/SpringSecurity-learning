package com.security.learn.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;
import java.net.PasswordAuthentication;

@Configuration
public class ProjectSecurityConfig {

    @Bean
    SecurityFilterChain customSecurityFilterChain(HttpSecurity http) throws  Exception{
        http.csrf().disable().authorizeHttpRequests()
                .requestMatchers("/myAccount","/myCards","/myBalance").authenticated()
                .requestMatchers("/contacts","/notices","/register").permitAll()
                .and().formLogin()
                .and().httpBasic();
        return http.build();
    }

    // creating inMemory User Detail to store the user id, password and role; and it is not recommended

//    @Bean
//    InMemoryUserDetailsManager userDetailsManager(){
//
//        UserDetails admin = User.withDefaultPasswordEncoder()
//                .username("admin")
//                .password("1234")
//                .authorities("ADMIN")
//                .build();
//        UserDetails user= User.withDefaultPasswordEncoder()
//                .username("user")
//                .password("1234")
//                .authorities("USER")
//                .build();
//        return new InMemoryUserDetailsManager(admin,user);
//
//
//    }

//    @Bean
//    UserDetailsService userDetailsService(DataSource dataSource){
//        return new JdbcUserDetailsManager(dataSource);
//    }


    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


}
