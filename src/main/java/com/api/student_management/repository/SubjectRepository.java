package com.api.student_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.student_management.entity.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {

}
