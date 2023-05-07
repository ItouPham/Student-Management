package com.api.student_management.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Logs {
	ERROR_SYSTEM("Error System"),
	GET_DATA_SUCCESS("Get Data Successfully"),
	INVALID_CODE("Invalid Code");
	private String message;
}
