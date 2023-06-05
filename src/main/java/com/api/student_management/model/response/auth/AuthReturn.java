package com.api.student_management.model.response.auth;

import com.api.student_management.model.response.NotificationResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthReturn {
	private AuthResponse authResponse;
	private NotificationResponse notification;
}
