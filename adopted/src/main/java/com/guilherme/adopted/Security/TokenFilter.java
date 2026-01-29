package com.guilherme.adopted.Security;

import java.io.IOException;
import java.util.Optional;


import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.guilherme.adopted.interfaces.ITokenService;
import com.guilherme.adopted.models.User;
import com.guilherme.adopted.repositories.UserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class TokenFilter extends OncePerRequestFilter {

    private ITokenService tokenService;

    private UserRepository userRepository;

    
    public TokenFilter(ITokenService tokenService,
            UserRepository userRepository) {
        this.tokenService = tokenService;
        this.userRepository = userRepository;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

               String token = getHeader(request);

               if(token != null){
                String email = this.tokenService.getEmail(token);
                Optional<User> user = this.userRepository.findByEmail(email);

                if(!user.isEmpty()){
                    authenticate(user.get());
                    filterChain.doFilter(request, response);
                    return;
                }

               }

               filterChain.doFilter(request, response);
    }


    public String getHeader(HttpServletRequest request) {
        String header = request.getHeader("Authorization");

        if(header != null && header.startsWith("Bearer ")){
            return header.substring(7);
        }
        return null;
    }

    public void authenticate(User user){
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

}
