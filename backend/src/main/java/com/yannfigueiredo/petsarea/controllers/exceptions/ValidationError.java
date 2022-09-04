package com.yannfigueiredo.petsarea.controllers.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {
	private static final long serialVersionUID = 1L;
	
	private List<FieldMessage> errors = new ArrayList<>();

	public List<FieldMessage> getFieldsMessages() {
		return errors;
	}
	
	public void addError(FieldMessage error) {
		this.errors.add(error);
	}
}
