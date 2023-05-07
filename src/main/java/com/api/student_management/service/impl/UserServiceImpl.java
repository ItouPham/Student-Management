package com.api.student_management.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.api.student_management.constant.RoleEnum;
import com.api.student_management.entity.Role;
import com.api.student_management.entity.User;
import com.api.student_management.model.response.ListUserReturn;
import com.api.student_management.model.response.NotificationResponse;
import com.api.student_management.model.response.ObjUser;
import com.api.student_management.repository.RoleRepository;
import com.api.student_management.repository.UserRepository;
import com.api.student_management.service.UserService;
import com.api.student_management.utils.Logs;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public ListUserReturn getUsersByCode(String code) {
		ListUserReturn listUserReturn = new ListUserReturn();
		List<ObjUser> listObjUser = new ArrayList<>();
		try {
			Optional<Role> role = roleRepository.findByName(code);
			List<User> users = userRepository.findByRoles(role);
			for (User user : users) {
				ObjUser objUser = new ObjUser();
				BeanUtils.copyProperties(user, objUser);
				listObjUser.add(objUser);
			}
			listUserReturn.setListUser(listObjUser);
			listUserReturn.setNotification(new NotificationResponse(Logs.GET_DATA_SUCCESS.getMessage()));
			return listUserReturn;
		} catch (Exception e) {
			e.printStackTrace();
			listUserReturn.setNotification(new NotificationResponse(Logs.ERROR_SYSTEM.getMessage()));
			return listUserReturn;
		}

	}

}
