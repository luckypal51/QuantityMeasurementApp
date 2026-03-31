package com.app.quantitymeasurement.util;

import com.app.quantitymeasurement.exception.AuthenticationException;
import com.app.quantitymeasurement.model.User;

public class AuthenticationValidation {

	public static final String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
	public static void validate(User user) throws AuthenticationException {
		if(user.getEmail()==null||user.getUsername()==null||user.getPassword()==null) {
			throw new AuthenticationException("Field cannot be null");
		}
		
		if(!user.getEmail().matches(regex)){
			throw new AuthenticationException("Not a valid Email");
		}
		
		if(user.getPassword().length()<4) {
			throw new AuthenticationException("Password length must be greater than 5");
		}
	}
}
