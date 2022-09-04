package com.yannfigueiredo.petsarea.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.yannfigueiredo.petsarea.controllers.exceptions.FieldMessage;
import com.yannfigueiredo.petsarea.dto.OwnerInsertDTO;
import com.yannfigueiredo.petsarea.entities.Owner;
import com.yannfigueiredo.petsarea.repositories.OwnerRepository;


public class OwnerInsertValidator implements ConstraintValidator<OwnerInsertValid, OwnerInsertDTO> {
	@Autowired
	OwnerRepository ownerRepository;
	
	@Override
	public void initialize(OwnerInsertValid ann) {
	}

	@Override
	public boolean isValid(OwnerInsertDTO dto, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
		
		Owner owner = ownerRepository.findByEmail(dto.getEmail());
		
		if(owner != null) {
			list.add(new FieldMessage("email", "O email inserido já existe."));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		
		return list.isEmpty();
	}
}