package com.api.student_management.controller;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.api.student_management.model.request.CreateUserRequest;
import com.api.student_management.model.request.UpdateUserRequest;
import com.api.student_management.model.response.NotificationResponse;
import com.api.student_management.model.response.user.ListUserReturn;
import com.api.student_management.model.response.user.UserReturn;
import com.api.student_management.service.UserService;
import com.api.student_management.utils.Logs;

@RestController
@RequestMapping("admin/user")
public class UserController {
	@Autowired
	private UserService userService;

	@GetMapping()
	@RolesAllowed("ADMIN")
	public ResponseEntity<?> getAllUser() {
		ListUserReturn listUserReturn = new ListUserReturn();
		listUserReturn = userService.getAllUser();
		return ResponseEntity.ok(listUserReturn);

	}

	@GetMapping("getByCode")
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

	@GetMapping("/{id}")
	@RolesAllowed("ADMIN")
	public ResponseEntity<?> getUserById(@PathVariable Long id) {
		UserReturn userReturn = new UserReturn();
		try {
			userReturn = userService.getUserById(id);
			return ResponseEntity.ok(userReturn);
		} catch (Exception e) {
			e.printStackTrace();
			userReturn.setNotification(new NotificationResponse(Logs.ERROR_SYSTEM.getMessage()));
			return ResponseEntity.ok(userReturn);
		}
	}

	@PostMapping()
	@RolesAllowed("ADMIN")
	public ResponseEntity<?> createNewUser(@RequestBody @Valid CreateUserRequest request) {
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

	@PutMapping("/{id}")
	@RolesAllowed("ADMIN")
	public ResponseEntity<?> editUser(@PathVariable Long id, @RequestBody @Valid UpdateUserRequest request) {
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

	@DeleteMapping("/{id}")
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
