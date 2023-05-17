package com.api.student_management.model.response.user;

import java.util.List;

import com.api.student_management.model.response.NotificationResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListUserReturn {
	private List<ObjUser> listUser;
	private NotificationResponse notification;
}
