package com.api.student_management.entity;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "classes")
public class Classes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private ZonedDateTime createdDate;

	private ZonedDateTime startDate;

	private ZonedDateTime endDate;

	@ManyToMany
	@JoinTable(name = "users_classes", joinColumns = @JoinColumn(name = "class_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	private List<User> members = new ArrayList<>();

	@ManyToMany
	@JoinTable(name = "subject_classes", joinColumns = @JoinColumn(name = "class_id"), inverseJoinColumns = @JoinColumn(name = "subject_id"))
	private List<Subject> subjects = new ArrayList<>();
}
