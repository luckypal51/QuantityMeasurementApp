package com.app.quantitymeasurement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.quantitymeasurement.dto.AuthDtoRequest;
import com.app.quantitymeasurement.exception.AuthenticationException;
import com.app.quantitymeasurement.model.User;
import com.app.quantitymeasurement.services.AuthService;
import com.app.quantitymeasurement.util.AuthenticationValidation;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

	@Autowired
	private AuthService authService;
	
	@PostMapping("/signup")
	public String signup(@RequestBody User user) throws AuthenticationException {
		AuthenticationValidation.validate(user);
		return authService.signup(user);
	}
	
	@PostMapping("/signin")
	public ResponseEntity<String> signin(@RequestBody AuthDtoRequest authDtoRequest) {
		return ResponseEntity.ok(authService.signin(authDtoRequest));
	}
}
