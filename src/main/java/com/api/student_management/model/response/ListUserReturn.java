package com.api.student_management.model.response;

import java.util.List;

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
