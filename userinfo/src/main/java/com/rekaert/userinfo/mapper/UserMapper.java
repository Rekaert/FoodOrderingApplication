package com.rekaert.userinfo.mapper;

import com.rekaert.userinfo.dto.UserDTO;
import com.rekaert.userinfo.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

	User mapUserDTOToUser(UserDTO userDTO);
	UserDTO mapUserToUserDTO(User user);
}
