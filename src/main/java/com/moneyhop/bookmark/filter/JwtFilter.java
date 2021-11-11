package com.moneyhop.bookmark.filter;

import com.moneyhop.bookmark.service.MyUserDetailsService;
import com.moneyhop.bookmark.utility.JWTUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JWTUtility jwtUtility;

    @Autowired
    private MyUserDetailsService myUserDetailsService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authorization = request.getHeader("Authorization");
        String token = null;
        String userName = null;

        if( null != authorization && authorization.startsWith("Bearer ") ){

            token = authorization.substring(7);
            userName = jwtUtility.getUsernameFromToken(token);

        }

        if( null != userName && null == SecurityContextHolder.getContext().getAuthentication() ){
            UserDetails userDetails = myUserDetailsService.loadUserByUsername(userName);

            if(jwtUtility.validateToken(token,userDetails)){
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                        = new UsernamePasswordAuthenticationToken(
                                userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities()
                );

                usernamePasswordAuthenticationToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
            filterChain.doFilter(request,response);
        }

    }
}
