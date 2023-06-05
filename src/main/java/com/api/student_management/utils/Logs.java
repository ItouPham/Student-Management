package com.api.student_management.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Logs {
	ERROR_SYSTEM("Error system"),
	GET_DATA_SUCCESS("Get data successfully"),
	GET_DATA_UNSUCCESS("Get data unsuccessfully"),
	ROLE_NOT_EXISTS("Role %ROLE% not exist"),
	ROLE_ID_NOT_EXISTS("Role with %ID% not exist"),
	INVALID_CODE("Invalid code"),
	EXISTED_USERNAME("Username already in use"), 
	EXISTED_EMAIL("Email already In use"), 
	CREATE_USER_SUCCESS("Create user successfully"), 
	CREATE_USER_UNSUCCESS("Create user unsuccessfully"),
	USER_NOT_EXISTS("User not exists"), 
	UPDATE_USER_SUCCESS("Update user successfully"), 
	UPDATE_USER_UNSUCCESS("Update user unsuccessfully"), 
	DELETE_USER_SUCCESS("Delete user successfully"),
	CREATE_CLASS_SUCCESS("Create class successfully"),
	CREATE_CLASS_UNSUCCESS("Create class unsuccessfully"),
	SUBJECT_NOT_EXISTS("Subject not exists"),
	LOGIN_SUCCESS("Login successfully"),
	INVALID_USERNAME_PASSWORD("Invalid username or password"), 
	DELETE_USER_UNSUCCESS("Delete user unsuccessfully");

	private String message;
}
