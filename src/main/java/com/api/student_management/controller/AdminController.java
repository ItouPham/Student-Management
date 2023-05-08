package com.api.student_management.controller;

import javax.annotation.security.RolesAllowed;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.student_management.model.request.UpdateUserRequest;
import com.api.student_management.model.response.ListUserReturn;
import com.api.student_management.model.response.NotificationResponse;
import com.api.student_management.model.response.UserReturn;
import com.api.student_management.service.UserService;
import com.api.student_management.utils.Logs;

@RestController
@RequestMapping("admin")
public class AdminController {
	@Autowired
	private UserService userService;

	@GetMapping("user")
	public ResponseEntity<?> getAllUser() {
		ListUserReturn listUserReturn = new ListUserReturn();
		try {
			listUserReturn = userService.getAllUser();

			return ResponseEntity.ok(listUserReturn);
		} catch (Exception e) {
			e.printStackTrace();
			listUserReturn.setNotification(new NotificationResponse(Logs.ERROR_SYSTEM.getMessage()));
			return ResponseEntity.ok(listUserReturn);
		}
	}

	@GetMapping("getUserByCode")
	@RolesAllowed("ADMIN")
	public ResponseEntity<?> getUsersByCode(String code) {
		ListUserReturn listUserReturn = new ListUserReturn();
		try {
			if (StringUtils.isNotEmpty(code)) {
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
	@RolesAllowed("ADMIN")
	public ResponseEntity<?> createNewUser(@RequestBody UpdateUserRequest request) {
		UserReturn userReturn = new UserReturn();
		try {
			userReturn = userService.createNewUser(request);
			return ResponseEntity.ok(userReturn);
		} catch (Exception e) {
			e.printStackTrace();
			userReturn.setNotification(new NotificationResponse(Logs.ERROR_SYSTEM.getMessage()));
			return ResponseEntity.ok(userReturn);
		}
	}

	@PutMapping("user/{id}")
	@RolesAllowed("ADMIN")
	public ResponseEntity<?> editUser(@PathVariable Long id, @RequestBody UpdateUserRequest request) {
		UserReturn userReturn = new UserReturn();
		try {
			userReturn = userService.editUser(id, request);
			return ResponseEntity.ok(userReturn);
		} catch (Exception e) {
			e.printStackTrace();
			userReturn.setNotification(new NotificationResponse(Logs.ERROR_SYSTEM.getMessage()));
			return ResponseEntity.ok(userReturn);
		}
	}

	@DeleteMapping("user/{id}")
	@RolesAllowed("ADMIN")
	public ResponseEntity<?> deleteUser(@PathVariable Long id) {
		UserReturn userReturn = new UserReturn();
		try {
			userReturn = userService.deleteUser(id);
			return ResponseEntity.ok(userReturn);
		} catch (Exception e) {
			e.printStackTrace();
			userReturn.setNotification(new NotificationResponse(Logs.ERROR_SYSTEM.getMessage()));
			return ResponseEntity.ok(userReturn);
		}
	}

}
