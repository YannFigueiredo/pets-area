package com.yannfigueiredo.petsarea.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.yannfigueiredo.petsarea.entities.Role;
import com.yannfigueiredo.petsarea.entities.enums.Gender;

public class OwnerInsertDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String firstName;
	private String lastName;
	private Integer age;
	private Gender gender;
	private String photo;
	private String email;
	private String password;
	
	private Set<Role> roles = new HashSet<>();
	
	public OwnerInsertDTO() {}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRoles() {
		return roles;
	}
}
