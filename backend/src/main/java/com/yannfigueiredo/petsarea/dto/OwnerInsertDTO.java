package com.yannfigueiredo.petsarea.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.yannfigueiredo.petsarea.services.validation.OwnerInsertValid;

@OwnerInsertValid
public class OwnerInsertDTO extends OwnerDTO {
	private static final long serialVersionUID = 1L;
	
	@Size(min = 6, max = 14, message = "A senha deve ter entre 6 e 14 caracteres.")
	@NotBlank(message = "Campo obrigatório.")
	private String password;
	
	public OwnerInsertDTO() {
		super();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
