package com.api.student_management.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.student_management.jwt.JwtTokenUtil;
import com.api.student_management.model.request.AuthRequest;
import com.api.student_management.model.response.AuthResponse;
import com.api.student_management.security.CustomUserDetails;

import io.jsonwebtoken.ExpiredJwtException;

@RestController
@RequestMapping("auth")
public class AuthController {
	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private JwtTokenUtil jwtUtil;

	@PostMapping("login")
	public ResponseEntity<?> login(@RequestBody @Valid AuthRequest request) {
		try {
			Authentication authentication = authManager
					.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

			CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();
			String accessToken = jwtUtil.generateAccessToken(user.getUsername());
			AuthResponse response = new AuthResponse(user.getUsername(), accessToken);

			return ResponseEntity.ok().body(response);

		} catch (BadCredentialsException ex) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}
	@GetMapping("validate")
	public ResponseEntity<?> validateToken(@RequestParam String token, @AuthenticationPrincipal CustomUserDetails user){
		try {
			Boolean isValidToken = jwtUtil.validateToken(token, user);
			return ResponseEntity.ok(isValidToken);
		} catch (ExpiredJwtException e) {
			return ResponseEntity.ok(false);
		}
	}
}
