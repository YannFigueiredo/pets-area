package com.yannfigueiredo.petsarea.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.yannfigueiredo.petsarea.controllers.exceptions.FieldMessage;
import com.yannfigueiredo.petsarea.dto.OwnerUpdateDTO;
import com.yannfigueiredo.petsarea.entities.Owner;
import com.yannfigueiredo.petsarea.repositories.OwnerRepository;


public class OwnerUpdateValidator implements ConstraintValidator<OwnerUpdateValid, OwnerUpdateDTO> {
	@Autowired 
	private HttpServletRequest request; 
	
	@Autowired
	private OwnerRepository ownerRepository;
	
	@Override
	public void initialize(OwnerUpdateValid ann) {
	}

	@Override
	public boolean isValid(OwnerUpdateDTO dto, ConstraintValidatorContext context) {
		
		@SuppressWarnings("unchecked")
		var uriVars = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		long useId = Long.parseLong(uriVars.get("id"));
		
		List<FieldMessage> list = new ArrayList<>();
		
		Owner owner = ownerRepository.findByEmail(dto.getEmail());
		
		if(owner != null && useId != owner.getId()) {
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