package com.api.student_management.model.request;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {
	@NotNull(message = "The username is required")
	@NotBlank(message = "The username cannot be empty")
	private String username;

	@NotNull(message = "The password is required")
	@NotBlank(message = "The password cannot be empty")
	@Length(min = 6, message = "Passwords must be at least 6 characters")
	private String password;

	@NotNull(message = "The email is required")
	@NotBlank(message = "The email cannot be empty")
	@Email(message = "Invalid email")
	private String email;
	
	private String fullName;

	private Integer age;

	private Set<Integer> roleIds = new HashSet<>();
}
