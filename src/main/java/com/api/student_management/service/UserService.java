package com.api.student_management.service;

import com.api.student_management.model.request.UpdateUserRequest;
import com.api.student_management.model.response.ListUserReturn;
import com.api.student_management.model.response.UserReturn;

public interface UserService {

	ListUserReturn getAllUser();

	ListUserReturn getUsersByCode(String code);

	UserReturn createNewUser(UpdateUserRequest request);

	UserReturn editUser(Long id, UpdateUserRequest request);

	UserReturn deleteUser(Long id);


}
