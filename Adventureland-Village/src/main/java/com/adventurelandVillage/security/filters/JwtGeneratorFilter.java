package com.adventurelandVillage.security.filters;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.adventurelandVillage.security.SecurityConstant;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtGeneratorFilter extends OncePerRequestFilter{
	   @Override
	    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
	            throws ServletException, IOException {
	        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	        SecretKey secretKey = Keys.hmacShaKeyFor(SecurityConstant.JWT_KEY.getBytes());
	        if (null != authentication) {
	            String jwt = null;
				try {
					jwt = Jwts.builder()
					            .setIssuer(SecurityConstant.JWT_ISSUER)
					            .setSubject("JWT Token")
					            .claim("username", authentication.getName())
					            .claim("authorities", populateAuthorities(authentication.getAuthorities()))
					            .setIssuedAt(new Date())
					            .setExpiration(new Date(new Date().getTime()+ 30000000))
					            .signWith(secretKey)
					            .compact();
				} catch (Exception e) {
					e.printStackTrace();
				}
	            
	            response.setHeader(SecurityConstant.JWT_HEADER, jwt);
	        }
	        filterChain.doFilter(request, response);
	    }
	    public String populateAuthorities(Collection<? extends GrantedAuthority> authorities) {
	        Set<String> authoritiesSet = new HashSet<>();
	        for (GrantedAuthority grantedAuthority : authorities) {
	            authoritiesSet.add(grantedAuthority.getAuthority());
	        }
	        return String.join(",", authoritiesSet);
	    }

	    @Override
	    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
	        return !request.getServletPath().equals("/customers/signIn") && !request.getServletPath().equals("/admin/signIn");
	    }
}
