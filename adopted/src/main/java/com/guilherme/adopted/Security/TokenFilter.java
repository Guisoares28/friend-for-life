package com.guilherme.adopted.Security;

import java.io.IOException;
import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
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

    private AuthenticationManager authenticationManager;

    private ITokenService tokenService;

    private UserRepository userRepository;

    
    public TokenFilter(AuthenticationManager authenticationManager, ITokenService tokenService,
            UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.userRepository = userRepository;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

                if(request.getRequestURI().contains("/h2-console")){
                    filterChain.doFilter(request, response);
                    return;
                }

                String token = getHeader(request);
                String email = "";
                User user = null;
                UsernamePasswordAuthenticationToken authentication = null;
                Optional<User> userFound = null;

                if(token == null){
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.setContentType("application/json");
                    response.getWriter().write("""
                            {
                                "Status": 401,
                                "Error" : "Unauthorized",
                                "message": "Token invalid"
                            }
                            """);
                        return;
                }

                if(token != null ){
                    email = this.tokenService.getEmail(token); //já valida o token
                    userFound = this.userRepository.findByEmail(email);
                }
                
                if(userFound != null && !userFound.isEmpty()){
                    user = userFound.get();
                    authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authentication);
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

}
