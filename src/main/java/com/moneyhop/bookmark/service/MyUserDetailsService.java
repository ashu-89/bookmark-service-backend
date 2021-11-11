package com.moneyhop.bookmark.service;

import com.moneyhop.bookmark.entity.Users;
import com.moneyhop.bookmark.repository.UserRepository;
import com.moneyhop.bookmark.utility.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        Users user = userRepository.findUserByUserName(userName);

        if(null == user)
            throw new UsernameNotFoundException("User Not Found");

        return new MyUserDetails(user);

    }
}
