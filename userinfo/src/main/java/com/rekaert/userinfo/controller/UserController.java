package com.rekaert.userinfo.controller;

import com.rekaert.userinfo.dto.UserDTO;
import com.rekaert.userinfo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping("/addUser")
	public ResponseEntity<UserDTO> saveUserDetails (@RequestBody UserDTO userDTO) {
		UserDTO savedUser = userService.addUser(userDTO);
		return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
	}

	@GetMapping("/fetchUserById/{id}")
	public ResponseEntity<UserDTO> fetchUserDetailsById (@PathVariable Integer userId) {
		return userService.fetchUserDetailsById(userId);
	}
}
