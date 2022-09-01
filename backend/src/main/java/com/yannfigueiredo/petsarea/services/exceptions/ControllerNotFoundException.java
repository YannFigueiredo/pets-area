package com.yannfigueiredo.petsarea.services.exceptions;

public class ControllerNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public ControllerNotFoundException(String msg) {
		super(msg);
	}
}
