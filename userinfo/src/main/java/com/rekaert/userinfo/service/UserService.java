package com.rekaert.userinfo.service;

import com.rekaert.userinfo.dto.UserDTO;
import com.rekaert.userinfo.entity.User;
import com.rekaert.userinfo.mapper.UserMapper;
import com.rekaert.userinfo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;

	public UserDTO addUser(UserDTO userDTO) {
		User savedUser = userRepository.save(UserMapper.INSTANCE.mapUserDTOToUser(userDTO));
		return UserMapper.INSTANCE.mapUserToUserDTO(savedUser);
	}

	public ResponseEntity<UserDTO> fetchUserDetailsById(Integer userId) {

		Optional<User> fetchedUser = userRepository.findById(userId);
		if(fetchedUser.isPresent()) {
			return new ResponseEntity<>(UserMapper.INSTANCE.mapUserToUserDTO(fetchedUser.get()), HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
}
