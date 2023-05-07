package com.api.student_management.controller;

import javax.annotation.security.RolesAllowed;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.student_management.model.request.AddUserRequest;
import com.api.student_management.model.response.ListUserReturn;
import com.api.student_management.model.response.NotificationResponse;
import com.api.student_management.service.UserService;
import com.api.student_management.utils.Logs;

@RestController
@RequestMapping("admin")
public class AdminController {
	@Autowired
	private UserService userService;

	@GetMapping("user")
	@RolesAllowed("ADMIN")
	public ResponseEntity<?> getUsersByCode(String code) {
		ListUserReturn listUserReturn = new ListUserReturn();
		try {
			if(StringUtils.isNotEmpty(code)) {
				listUserReturn = userService.getUsersByCode(code);				
			} else {
				listUserReturn.setNotification(new NotificationResponse(Logs.INVALID_CODE.getMessage()));
			}
			return ResponseEntity.ok(listUserReturn);
		} catch (Exception e) {
			e.printStackTrace();
			listUserReturn.setNotification(new NotificationResponse(Logs.ERROR_SYSTEM.getMessage()));
			return ResponseEntity.ok(listUserReturn);
		}
	}

	@PostMapping("user")
	public ResponseEntity<?> createNewUser(@RequestBody AddUserRequest request) {
		return ResponseEntity.ok(null);
	}
}
