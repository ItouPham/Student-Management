package com.api.student_management.model.response.user;

import com.api.student_management.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties("password")
public class ObjUser extends User{

}
