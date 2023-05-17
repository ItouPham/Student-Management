package com.api.student_management.model.response.classes;

import com.api.student_management.entity.Classes;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties({"createdDate","startDate"})
public class ObjClass extends Classes{
	private String createdDates;
	private String startDates;
}
