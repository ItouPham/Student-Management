package com.api.student_management.service.impl;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.student_management.entity.Classes;
import com.api.student_management.entity.Subject;
import com.api.student_management.entity.User;
import com.api.student_management.model.request.ClassRequest;
import com.api.student_management.model.response.NotificationResponse;
import com.api.student_management.model.response.classes.ClassReturn;
import com.api.student_management.model.response.classes.ObjClass;
import com.api.student_management.repository.ClassRepository;
import com.api.student_management.repository.SubjectRepository;
import com.api.student_management.repository.UserRepository;
import com.api.student_management.service.ClassService;
import com.api.student_management.utils.DateTimeUtil;
import com.api.student_management.utils.Logs;

@Service
public class ClassServiceImpl implements ClassService {

	@Autowired
	private ClassRepository classRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private SubjectRepository subjectRepository;

	@Override
	public ClassReturn createNewClass(ClassRequest request) {
		ClassReturn classReturn = new ClassReturn();
		ObjClass objClass = new ObjClass();
		Classes classes = new Classes();
		List<User> listMembers = new ArrayList<>();
		List<Subject> listSubjects = new ArrayList<>();
		try {
			BeanUtils.copyProperties(request, classes);
			for (Long id : request.getMemberIds()) {
				User member = userRepository.findById(id).orElse(null);
				if (member != null) {
					listMembers.add(member);
				} else {
					classReturn.setNotification(new NotificationResponse(Logs.USER_NOT_EXISTS.getMessage()));
					return classReturn;
				}
			}
			for (Integer id : request.getSubjectIds()) {
				Subject subject = subjectRepository.findById(id).orElse(null);
				if (subject != null) {
					listSubjects.add(subject);
				} else {
					classReturn.setNotification(new NotificationResponse(Logs.SUBJECT_NOT_EXISTS.getMessage()));
					return classReturn;
				}
			}
			classes.setCreatedDate(ZonedDateTime.now());
			classes.setStartDate(DateTimeUtil.format(request.getStartDate()));
			classes.setMembers(listMembers);
			classes.setSubjects(listSubjects);
			classRepository.save(classes);
			BeanUtils.copyProperties(classes, objClass);
			objClass.setCreatedDates(DateTimeUtil.formatRespones(objClass.getCreatedDate()));
			objClass.setStartDates(DateTimeUtil.formatRespones(objClass.getStartDate()));
			classReturn.setObjClass(objClass);
			classReturn.setNotification(new NotificationResponse(Logs.CREATE_CLASS_SUCCESS.getMessage()));
			return classReturn;
		} catch (Exception e) {
			e.printStackTrace();
			classReturn.setNotification(new NotificationResponse(Logs.ERROR_SYSTEM.getMessage()));
			return classReturn;
		}
	}

}
