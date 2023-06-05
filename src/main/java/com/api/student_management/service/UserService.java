package com.api.student_management.service;

import com.api.student_management.model.request.CreateUserRequest;
import com.api.student_management.model.request.UpdateUserRequest;
import com.api.student_management.model.response.user.ListUserReturn;
import com.api.student_management.model.response.user.UserReturn;

public interface UserService {

	ListUserReturn getAllUser();

	ListUserReturn getUsersByCode(String code);
	
	UserReturn getUserById(Long id);

	UserReturn createNewUser(CreateUserRequest request);

	UserReturn editUser(Long id, UpdateUserRequest request);

	UserReturn deleteUser(Long id);

}
