package com.api.student_management.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.student_management.entity.Role;
import com.api.student_management.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByEmail(String email);

	Optional<User> findByUsername(String username);

	List<User> findByRoles(Optional<Role> role);

	boolean existsByUsername(String username);

	boolean existsByEmail(String email);
}
