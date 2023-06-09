package com.api.student_management.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.api.student_management.constant.RoleEnum;
import com.api.student_management.entity.Role;
import com.api.student_management.entity.User;
import com.api.student_management.model.request.CreateUserRequest;
import com.api.student_management.model.request.UpdateUserRequest;
import com.api.student_management.model.response.NotificationResponse;
import com.api.student_management.model.response.user.ListUserReturn;
import com.api.student_management.model.response.user.ObjUser;
import com.api.student_management.model.response.user.UserReturn;
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

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public ListUserReturn getAllUser() {
		ListUserReturn listUserReturn = new ListUserReturn();
		List<ObjUser> listObjUser = new ArrayList<>();
		try {
			List<User> users = userRepository.findAll();
			if (users != null && users.size() > 0) {
				for (User user : users) {
					ObjUser objUser = new ObjUser();
					BeanUtils.copyProperties(user, objUser);
					listObjUser.add(objUser);
				}
				listUserReturn.setNotification(new NotificationResponse(Logs.GET_DATA_SUCCESS.getMessage()));
			} else {
				listUserReturn.setNotification(new NotificationResponse(Logs.GET_DATA_UNSUCCESS.getMessage()));
			}
			listUserReturn.setListUser(listObjUser);
			return listUserReturn;
		} catch (Exception e) {
			e.printStackTrace();
			listUserReturn.setNotification(new NotificationResponse(Logs.ERROR_SYSTEM.getMessage()));
			return listUserReturn;
		}
	}

	@Override
	public ListUserReturn getUsersByCode(String code) {
		ListUserReturn listUserReturn = new ListUserReturn();
		List<ObjUser> listObjUser = new ArrayList<>();
		try {
			Optional<Role> role = roleRepository.findByName(code.toUpperCase());
			if (role.isPresent()) {
				List<User> users = userRepository.findByRoles(role);
				if (users != null && users.size() > 0) {
					for (User user : users) {
						ObjUser objUser = new ObjUser();
						BeanUtils.copyProperties(user, objUser);
						listObjUser.add(objUser);
					}
					listUserReturn.setNotification(new NotificationResponse(Logs.GET_DATA_SUCCESS.getMessage()));
				} else {
					listUserReturn.setNotification(new NotificationResponse(Logs.GET_DATA_UNSUCCESS.getMessage()));
				}
			} else {
				listUserReturn.setNotification(new NotificationResponse(Logs.ROLE_NOT_EXISTS.getMessage()));
			}
			listUserReturn.setListUser(listObjUser);
			return listUserReturn;
		} catch (Exception e) {
			e.printStackTrace();
			listUserReturn.setNotification(new NotificationResponse(Logs.ERROR_SYSTEM.getMessage()));
			return listUserReturn;
		}

	}

	@Override
	public UserReturn getUserById(Long id) {
		UserReturn userReturn = new UserReturn();
		User user = new User();
		ObjUser objUser = new ObjUser();
		try {
			user = userRepository.findById(id).orElse(null);
			if (user != null) {
				BeanUtils.copyProperties(user, objUser);
				userReturn.setObjUser(objUser);
				userReturn.setNotification(new NotificationResponse(Logs.GET_DATA_SUCCESS.getMessage()));
			} else {
				userReturn.setNotification(new NotificationResponse(Logs.USER_NOT_EXISTS.getMessage()));
			}
			return userReturn;
		} catch (Exception e) {
			e.printStackTrace();
			userReturn.setNotification(new NotificationResponse(Logs.ERROR_SYSTEM.getMessage()));
			return userReturn;
		}
	}

	@Override
	@Transactional
	public UserReturn createNewUser(CreateUserRequest request) {
		UserReturn userReturn = new UserReturn();
		User user = new User();
		ObjUser objUser = new ObjUser();
		Set<Role> roles = new HashSet<>();
		try {
			boolean isExistedUsername = userRepository.existsByUsername(request.getUsername());
			boolean isExistedEmail = userRepository.existsByEmail(request.getEmail());
			if (isExistedUsername) {
				userReturn.setNotification(new NotificationResponse(Logs.EXISTED_USERNAME.getMessage()));
				return userReturn;
			}

			if (isExistedEmail) {
				userReturn.setNotification(new NotificationResponse(Logs.EXISTED_EMAIL.getMessage()));
				return userReturn;
			}
			request.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
			BeanUtils.copyProperties(request, user);
			for (Integer roleId : request.getRoleIds()) {
				Role role = roleRepository.findById(roleId).orElse(null);
				if (role != null) {
					roles.add(role);
				} else {
					String message = Logs.ROLE_ID_NOT_EXISTS.getMessage().replace("%ID%", roleId.toString());
					userReturn.setNotification(new NotificationResponse(message));
					return userReturn;
				}
			}
			user.setRoles(roles);
			user = userRepository.save(user);
			if (user != null) {
				BeanUtils.copyProperties(user, objUser);
				userReturn.setObjUser(objUser);
				userReturn.setNotification(new NotificationResponse(Logs.CREATE_USER_SUCCESS.getMessage()));
			} else {
				userReturn.setNotification(new NotificationResponse(Logs.CREATE_USER_UNSUCCESS.getMessage()));
			}
			return userReturn;
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			userReturn.setNotification(new NotificationResponse(Logs.ERROR_SYSTEM.getMessage()));
			return userReturn;
		}
	}

	@Override
	@Transactional
	public UserReturn editUser(Long id, UpdateUserRequest request) {
		UserReturn userReturn = new UserReturn();
		User user = new User();
		ObjUser objUser = new ObjUser();
		Set<Role> roles = new HashSet<>();
		try {
			user = userRepository.findById(id).orElse(null);
			if (user != null) {
				if (!user.getEmail().equals(request.getEmail())) {
					boolean isExistedEmail = userRepository.existsByEmail(request.getEmail());
					if (isExistedEmail) {
						userReturn.setNotification(new NotificationResponse(Logs.EXISTED_EMAIL.getMessage()));
						return userReturn;
					}
				}
				if (StringUtils.isNotEmpty(request.getPassword())) {
					user.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
				}
				user.setEmail(request.getEmail());
				user.setAge(request.getAge());
				user.setFullName(request.getFullName());
				for (Integer roleId : request.getRoleIds()) {
					Role role = roleRepository.findById(roleId).orElse(null);
					if (role != null) {
						roles.add(role);
					}
				}
				user.setRoles(roles);
				user = userRepository.save(user);
				if (user != null) {
					BeanUtils.copyProperties(user, objUser);
					userReturn.setObjUser(objUser);
					userReturn.setNotification(new NotificationResponse(Logs.UPDATE_USER_SUCCESS.getMessage()));
				} else {
					userReturn.setNotification(new NotificationResponse(Logs.UPDATE_USER_UNSUCCESS.getMessage()));
				}
			} else {
				userReturn.setNotification(new NotificationResponse(Logs.USER_NOT_EXISTS.getMessage()));
			}
			return userReturn;
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			userReturn.setNotification(new NotificationResponse(Logs.ERROR_SYSTEM.getMessage()));
			return userReturn;
		}
	}

	@Override
	@Transactional
	public UserReturn deleteUser(Long id) {
		UserReturn userReturn = new UserReturn();
		User user = new User();
		try {
			user = userRepository.findById(id).orElse(null);
			if (user != null) {
				userRepository.delete(user);
				userReturn.setObjUser(null);
				userReturn.setNotification(new NotificationResponse(Logs.DELETE_USER_SUCCESS.getMessage()));
			} else {
				userReturn.setNotification(new NotificationResponse(Logs.USER_NOT_EXISTS.getMessage()));
			}
			return userReturn;
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			userReturn.setNotification(new NotificationResponse(Logs.ERROR_SYSTEM.getMessage()));
			return userReturn;
		}
	}

}
