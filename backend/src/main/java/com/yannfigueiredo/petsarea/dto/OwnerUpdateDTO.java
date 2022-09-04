package com.yannfigueiredo.petsarea.dto;

import com.yannfigueiredo.petsarea.services.validation.OwnerUpdateValid;

@OwnerUpdateValid
public class OwnerUpdateDTO extends OwnerDTO {
	private static final long serialVersionUID = 1L;
	
	public OwnerUpdateDTO() {
		super();
	}
}
