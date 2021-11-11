package com.moneyhop.bookmark.controller;

import com.moneyhop.bookmark.model.request.JwtRequest;
import com.moneyhop.bookmark.model.response.JwtResponse;
import com.moneyhop.bookmark.service.MyUserDetailsService;
import com.moneyhop.bookmark.utility.JWTUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class LoginController {

    @Autowired
    private JWTUtility jwtUtility;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @PostMapping("/login")
    public JwtResponse login(  @RequestBody JwtRequest jwtRequest) throws Exception {

        //Check if username and password are authentic
        try{

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                        jwtRequest.getUserName(),
                        jwtRequest.getPassword()
                    )
            );
        } catch (BadCredentialsException e){
            throw new Exception( "Invalid Credentials", e);
        }

        //create jwt token

        //create user details
        final UserDetails userDetails = myUserDetailsService.loadUserByUsername(jwtRequest.getUserName());

        //create jwt token
        final String token = jwtUtility.generateToken(userDetails);

        return new JwtResponse(token);
    }
}
