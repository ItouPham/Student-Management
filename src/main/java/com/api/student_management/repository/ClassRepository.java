package com.api.student_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.student_management.entity.Classes;

public interface ClassRepository extends JpaRepository<Classes, Long> {

}
