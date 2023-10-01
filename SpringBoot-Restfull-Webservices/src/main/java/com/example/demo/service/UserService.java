package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.UserDto;

public interface UserService {

	public UserDto saveUser(UserDto user);
	public UserDto getUserById(Long id);
	public List<UserDto> getAllUsers();
	public UserDto updateUser(UserDto user);
	public void deleteUserById(Long id);

}
