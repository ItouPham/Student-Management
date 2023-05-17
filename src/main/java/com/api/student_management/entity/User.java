package com.api.student_management.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User implements Cloneable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String username;

	@JsonIgnore
	private String password;

	private String fullName;

	private Integer age;

	private String email;

	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

	public User(String username, String password, String fullName, int age, String email) {
		this.username = username;
		this.password = password;
		this.fullName = fullName;
		this.age = age;
		this.email = email;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
