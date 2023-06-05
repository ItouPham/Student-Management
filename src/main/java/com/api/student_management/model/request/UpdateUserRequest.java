package com.api.student_management.model.request;

import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequest {
	
	private String password;

	private String fullName;

	private Integer age;

	private String email;
	
	private Set<Integer> roleIds = new HashSet<>();
}
