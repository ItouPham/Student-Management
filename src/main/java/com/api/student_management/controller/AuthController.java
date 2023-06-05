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
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.api.student_management.jwt.JwtTokenUtil;
import com.api.student_management.model.request.AuthRequest;
import com.api.student_management.model.response.NotificationResponse;
import com.api.student_management.model.response.auth.AuthResponse;
import com.api.student_management.model.response.auth.AuthReturn;
import com.api.student_management.security.CustomUserDetails;
import com.api.student_management.utils.Logs;

import io.jsonwebtoken.ExpiredJwtException;

@RestController
@RequestMapping("auth")
public class AuthController {
	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private JwtTokenUtil jwtUtil;
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public NotificationResponse handleValidationExceptions(MethodArgumentNotValidException ex) {
		NotificationResponse notificationResponse = new NotificationResponse();
		notificationResponse.setMessage(ex.getBindingResult().getFieldError().getDefaultMessage());
        return notificationResponse;
    }

	@PostMapping("login")
	public ResponseEntity<?> login(@RequestBody @Valid AuthRequest request) {
		AuthReturn authReturn = new AuthReturn();
		Authentication authentication = null;
		try {
			try {
				authentication = authManager.authenticate(
						new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
			} catch (BadCredentialsException ex) {
				authReturn.setNotification(new NotificationResponse(Logs.INVALID_USERNAME_PASSWORD.getMessage()));
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(authReturn);
			}

			CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();
			String accessToken = jwtUtil.generateAccessToken(user.getUsername());
			authReturn.setAuthResponse(new AuthResponse(user.getUsername(), accessToken));
			authReturn.setNotification(new NotificationResponse(Logs.LOGIN_SUCCESS.getMessage()));
			return ResponseEntity.ok().body(authReturn);

		} catch (Exception e) {
			e.printStackTrace();
			authReturn.setNotification(new NotificationResponse(Logs.ERROR_SYSTEM.getMessage()));
			return ResponseEntity.ok(authReturn);
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
