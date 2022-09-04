package com.yannfigueiredo.petsarea.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import com.yannfigueiredo.petsarea.entities.Role;
import com.yannfigueiredo.petsarea.entities.enums.Gender;

public class OwnerInsertDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Size(min = 2, max = 30, message = "O nome deve ter entre 2 e 30 caracteres.")
	@NotBlank(message = "Campo obrigatório.")
	private String firstName;
	
	@Size(min = 2, max = 30, message = "O sobrenome deve ter entre 2 e 30 caracteres.")
	@NotBlank(message = "Campo obrigatório.")
	private String lastName;
	
	@PositiveOrZero(message = "O valor deve ser 0 ou maior.")
	private Integer age;
	
	@NotNull(message = "Deve ser escolhido um gênero.")
	private Gender gender;
	
	private String photo;
	
	@Email(message = "O email inserido deve ser válido.")
	private String email;
	
	@Size(min = 6, max = 14, message = "A senha deve ter entre 6 e 14 caracteres.")
	@NotBlank(message = "Campo obrigatório.")
	private String password;
	
	@NotEmpty(message = "Deve existir pelo menos uma role.")
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
