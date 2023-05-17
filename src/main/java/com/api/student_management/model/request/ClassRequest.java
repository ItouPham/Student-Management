package com.api.student_management.model.request;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassRequest {
	
	private Long id;
	
	private String name;

	private String startDate;

	private String endDate;

	private List<Long> memberIds = new ArrayList<>();

	private List<Integer> subjectIds = new ArrayList<>();
}
