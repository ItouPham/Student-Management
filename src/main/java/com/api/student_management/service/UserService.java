package com.api.student_management.service;

import com.api.student_management.model.response.ListUserReturn;

public interface UserService {

	ListUserReturn getUsersByCode(String code);

}
