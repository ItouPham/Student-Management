package com.api.student_management.model.request;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {
	@NotNull
	@Length(min = 5, max = 50)
	private String username;

	@NotNull
	@Length(min = 5, max = 10)
	private String password;
}