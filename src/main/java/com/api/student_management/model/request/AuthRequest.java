package com.api.student_management.model.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {
//	@NotNull(message = "The username is required")
	@NotBlank(message = "The username cannot be empty")
//	@Length(min = 5, max = 50)
	private String username;

//	@NotNull(message = "The password is required")
	@NotBlank(message = "The password cannot be empty")
//	@Length(min = 5, max = 10)
	private String password;
}