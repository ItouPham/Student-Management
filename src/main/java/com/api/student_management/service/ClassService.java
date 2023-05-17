package com.api.student_management.service;

import com.api.student_management.model.request.ClassRequest;
import com.api.student_management.model.response.classes.ClassReturn;

public interface ClassService {

	ClassReturn createNewClass(ClassRequest request);

}
