package com.medochemie.ordermanagement.usersservice.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import static com.medochemie.ordermanagement.usersservice.constant.SecurityConstant.*;
import static java.util.Arrays.stream;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.medochemie.ordermanagement.usersservice.constant.SecurityConstant;
import com.medochemie.ordermanagement.usersservice.entity.UserPrincipal;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

// whenever the application starts, spring will scan and create Bean for this class
@Component
public class JWTTokenProvider {
    // this should be stored in secured server and have it in properties file, @Value annotation tells spring to grab the value from application.yaml or application.properties file
    @Value("${jwt.secret}")
    private String secret;

    // generate jwt token after authentications 
    public String generateJwtToken(UserPrincipal userPrincipal) {
        String[] claims = generateClaimsFromUser(userPrincipal);
        return JWT.create()
                .withIssuer(SOLOMON)
                .withAudience(MC_ADMIN)
                .withIssuedAt(new Date())
                .withSubject(userPrincipal.getUsername())
                .withArrayClaim(AUTHORITIES, claims)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(secret.getBytes()));
    }

    //helper method
    private String[] generateClaimsFromUser(UserPrincipal user) {
        List<String> claims = new ArrayList<>();
        for(GrantedAuthority grantedAuthority : user.getAuthorities()) {
            claims.add(grantedAuthority.getAuthority());
        }
        return claims.toArray(new String[0]);
    }

    // get authorities from the token
    public List<GrantedAuthority> getAuthorities(String token) {
        String[] claims = getClaimsFromToken(token);
        return stream(claims).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    //helper method
    private String[] getClaimsFromToken(String token) {
        JWTVerifier verifier = getJWTVerifier();
        return verifier.verify(token).getClaim(AUTHORITIES).asArray(String.class);
    }

    //helper method
    private JWTVerifier getJWTVerifier() {
        JWTVerifier verifier;
        try {
            Algorithm algorithm = Algorithm.HMAC512(secret);
            verifier = JWT.require(algorithm).withIssuer(SOLOMON).build();
        }
        catch (JWTVerificationException exception) {
            //instead of throwing the exception which will reveal our application critical information, throw the TOKEN_CANNOT_BE_VERIFIED error we defined.
            throw new JWTVerificationException(TOKEN_CANNOT_BE_VERIFIED);
        }
        return verifier;
    }

    // once we verified the token, need to authenticate. The methods tell spring, hey this user is authenticated and pas the request
    public Authentication getAuthentication(
            String username,
            List<GrantedAuthority> authorities,
            HttpServletRequest request){
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new
                UsernamePasswordAuthenticationToken(username, null, authorities);
        usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetails(request));
        return usernamePasswordAuthenticationToken;
    }

    public boolean isTokenValid(String username, String token) {
        JWTVerifier verifier = getJWTVerifier();
        return StringUtils.isNotEmpty(username) && !isTokenExpired(verifier, token);
    }

    // helper method
    private boolean isTokenExpired(JWTVerifier verifier, String token) {
        Date expirationDate = verifier.verify(token).getExpiresAt();
        return expirationDate.before(new Date());
    }

    //get subject
    public String getSubject(String token){
        JWTVerifier verifier = getJWTVerifier();
        return verifier.verify(token).getSubject();
    }

    // need to build a filter that takes every request that comes to the application
    // to make sure that they're coming with the appropriate & valid token
    // and set the user as an authenticated user
//    JWTAuthorizationFilter
}
