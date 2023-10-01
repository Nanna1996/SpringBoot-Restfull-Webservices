package com.example.demo.mapper;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;


public class UserMapper {
	// Convert User JPA entity into UserDto Object

	public static UserDto mapToUserDto(User user) {
		UserDto userDto = new UserDto(user.getId(), user.getName(), user.getPhno(), user.getEmail());
		return userDto;
	}

	// Convert UserDto into User JPA entity
	public static User mapToUser(UserDto userDto) {
		User user = new User(userDto.getId(), userDto.getName(), userDto.getPhno(), userDto.getEmail());
		return user;
	}

}
