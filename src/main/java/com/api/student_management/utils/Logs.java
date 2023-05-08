package com.api.student_management.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Logs {
	ERROR_SYSTEM("Error system"),
	GET_DATA_SUCCESS("Get data successfully"), 
	INVALID_CODE("Invalid code"),
	EXISTED_USERNAME("Username already in use"), 
	EXISTED_EMAIL("Email already In use"), 
	CREATE_USER_SUCCESS("Create user successfully"), 
	USER_NOT_EXISTS("User not exists"), 
	UPDATE_USER_SUCCESS("Update user successfully"), 
	DELETE_USER_SUCCESS("Delete user successfully");

	private String message;
}
