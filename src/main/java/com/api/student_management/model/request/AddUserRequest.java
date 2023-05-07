package com.api.student_management.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddUserRequest {
	
	private String username;

	private String password;

	private String fullName;

	private Integer age;

	private String email;
	
	private Integer roleId;
}
