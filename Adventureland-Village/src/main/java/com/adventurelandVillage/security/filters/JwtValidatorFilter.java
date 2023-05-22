package com.adventurelandVillage.security.filters;

import java.io.IOException;
import java.util.List;

import javax.crypto.SecretKey;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.adventurelandVillage.security.SecurityConstant;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtValidatorFilter extends OncePerRequestFilter{

	 @Override
	    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		 SecretKey secretKey = Keys.hmacShaKeyFor(SecurityConstant.JWT_KEY.getBytes());
	        String jwt= request.getHeader(SecurityConstant.JWT_HEADER);	        
	        if(jwt != null) {
	            try {
	                jwt = jwt.substring(7);
	                Claims claims= Jwts.parserBuilder()
	                		.setSigningKey(secretKey)
	                		.build().parseClaimsJws(jwt).getBody();
	                String username= String.valueOf(claims.get("username"));
	                
	                String authorities= (String)claims.get("authorities");
	                
	                List<GrantedAuthority> authoritiesList = AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);
	                
					Authentication auth = new UsernamePasswordAuthenticationToken(username, null, authoritiesList);
					
	                SecurityContextHolder.getContext().setAuthentication(auth);
	            } catch (Exception e) {
	            	throw new BadCredentialsException("Invalid Token");        
	            }
	        }
	        filterChain.doFilter(request, response);
	    }

	    @Override
	    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
	        return request.getServletPath().equals("/customers/signIn") || request.getServletPath().equals("/admin/signIn");
	    }
}
