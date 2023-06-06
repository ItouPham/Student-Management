package com.api.student_management.exception;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.api.student_management.utils.Logs;

@RestControllerAdvice
public class CustomizedResponseEntityExceptionHandler {
	
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<?> handleInvalidUsernamePasswordException(Exception ex, WebRequest request) {
    	LocalDateTime curDate = LocalDateTime.now();
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		ErrorDetail errorDetail = new ErrorDetail(dateFormat.format(curDate),Logs.INVALID_USERNAME_PASSWORD.getMessage(), request.getDescription(false));
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorDetail);
    }
	
//	@Override
//	protected ResponseEntity<Object> handleMethodArgumentNotValid(
//			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//		LocalDateTime curDate = LocalDateTime.now();
//		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
//		ErrorDetail errorDetail = new ErrorDetail(dateFormat.format(curDate),ex.getFieldError().getDefaultMessage(), request.getDescription(false));
//		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetail);
//	}
	
//	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex, WebRequest request) {
		LocalDateTime curDate = LocalDateTime.now();
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		ErrorDetail errorDetail = new ErrorDetail(dateFormat.format(curDate), ex.getFieldError().getDefaultMessage(),
				request.getDescription(false));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetail);
	}

}
