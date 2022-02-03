package com.medochemie.ordermanagement.usersservice.utility;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.medochemie.ordermanagement.usersservice.constant.SecurityConstant.OPTIONS_HTTP_METHOD;
import static com.medochemie.ordermanagement.usersservice.constant.SecurityConstant.TOKEN_PREFIX;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

public class JWTAuthorizationFilter extends OncePerRequestFilter {

    private JWTTokenProvider jwtTokenProvider;

    public JWTAuthorizationFilter(JWTTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        // for request with method Option, we will let it go
        if(request.getMethod().equalsIgnoreCase(OPTIONS_HTTP_METHOD)) {
            response.setStatus(HttpStatus.OK.value());
        } else {
            String authorizationHeader = request.getHeader(AUTHORIZATION);
            if(StringUtils.isNotEmpty(authorizationHeader) ||
                    authorizationHeader.startsWith(TOKEN_PREFIX)) {
                // if this is true, we're letting the request continue since it is authorized token
                filterChain.doFilter(request, response);
                return;
            }
        }
    }
}
