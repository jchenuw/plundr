package com.iq3.plundr.security.jwt;

import com.iq3.plundr.security.UserDetailsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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

	private static final Logger log = LoggerFactory.getLogger(JwtUtil.class);

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws ServletException, IOException {

		String authHeader = req.getHeader("Authorization");

		if(authHeader != null && authHeader.startsWith("Bearer ")) {

			String jwtToken = authHeader.substring(7);

			if(jwtUtil.validateToken(jwtToken)) {
				String username = jwtUtil.getTokenUsername(jwtToken);

				UserDetails userDetails = userDetailsService.loadUserByUsername(username);

				Authentication authentication =
						new UsernamePasswordAuthenticationToken(userDetails, null, null);

				SecurityContextHolder.getContext().setAuthentication(authentication);

				log.debug("JWT Authentication Successful");
			}
		}

		chain.doFilter(req, res);
	}
}
