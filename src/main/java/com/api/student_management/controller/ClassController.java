package com.api.student_management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.student_management.model.request.ClassRequest;
import com.api.student_management.model.response.NotificationResponse;
import com.api.student_management.model.response.classes.ClassReturn;
import com.api.student_management.model.response.user.ListUserReturn;
import com.api.student_management.service.ClassService;
import com.api.student_management.utils.Logs;

@RestController
@RequestMapping("admin/class")
public class ClassController {
	
	@Autowired
	private ClassService classService;
	
	@PostMapping
	public ResponseEntity<?> createNewClass(@RequestBody ClassRequest request) {
		ListUserReturn listUserReturn = new ListUserReturn();
		ClassReturn classReturn = new ClassReturn();
		try {
			classReturn = classService.createNewClass(request);
			return ResponseEntity.ok(classReturn);
		} catch (Exception e) {
			e.printStackTrace();
			listUserReturn.setNotification(new NotificationResponse(Logs.ERROR_SYSTEM.getMessage()));
			return ResponseEntity.ok(classReturn);
		}
	}
}
