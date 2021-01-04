package com.iq3.plundr.controller;

import com.iq3.plundr.helper.LoginInfo;
import com.iq3.plundr.helper.SuccessLoginInfo;
import com.iq3.plundr.security.UserDetailsServiceImpl;
import com.iq3.plundr.security.jwt.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@PostMapping("/userLogin")
	public ResponseEntity<?> authenticate(@RequestBody LoginInfo loginInfo) {

		String username = loginInfo.getUsername();
		String password = loginInfo.getPassword();

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(username, password));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwtToken = jwtUtil.generateToken(authentication);

		return ResponseEntity.ok(new SuccessLoginInfo(jwtToken));

	}
}
