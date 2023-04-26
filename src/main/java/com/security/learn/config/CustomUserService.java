package com.security.learn.config;

import com.security.learn.model.CustomUser;
import com.security.learn.repository.CustomUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class CustomUserService implements UserDetailsService {

    @Autowired
    private CustomUserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String userName,password=null;
        List<SimpleGrantedAuthority> authorities= null;
        List<CustomUser> customUsers=userRepo.findByEmail(username);
        if(customUsers.size()==0) throw new UsernameNotFoundException("user not found");

        userName=customUsers.get(0).getEmail();
        password = customUsers.get(0).getPassword();
        authorities= new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(customUsers.get(0).getRole()));
        return new User(userName,password,authorities);


    }
}
