package com.api.student_management.model.response.classes;

import com.api.student_management.model.response.NotificationResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassReturn {
	private ObjClass objClass;
	private NotificationResponse notification;
}
