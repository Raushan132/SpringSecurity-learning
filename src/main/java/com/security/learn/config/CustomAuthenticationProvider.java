package com.security.learn.config;

import com.security.learn.model.CustomUser;
import com.security.learn.repository.CustomUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private CustomUserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username= authentication.getName();
        String password= authentication.getCredentials().toString();
        List<CustomUser> customUsers= userRepo.findByEmail(username);
        if(customUsers.size()==0) throw new UsernameNotFoundException(username +"not found");
        if(!passwordEncoder.matches(password,customUsers.get(0).getPassword()))
            throw new BadCredentialsException("Bad Credentials");
        List<SimpleGrantedAuthority> authorities= new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(customUsers.get(0).getRole()));
        return new UsernamePasswordAuthenticationToken(username,password,authorities);



    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
