package com.api.student_management.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserReturn {
	private ObjUser objUser;
	private NotificationResponse notification;
}
