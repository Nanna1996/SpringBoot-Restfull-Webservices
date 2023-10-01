package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import com.example.demo.exception.EmailAlreadyExistsException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.mapper.AutoUserMapper;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final @NonNull UserRepository repository;

	private final @NonNull ModelMapper modelMapper;

	@Override
	public UserDto saveUser(UserDto userDto) {
		// Convert User Dto entity into User Jpa entity

//		User user = UserMapper.mapToUser(userDto);

//		User user = modelMapper.map(userDto, User.class);
		Optional<User> optionaUser = repository.findByEmail(userDto.getEmail());
		if (optionaUser.isPresent()) {
			throw new EmailAlreadyExistsException("Email already exists for User");
		}

		User user = AutoUserMapper.MAPPER.mapToUser(userDto);

		User savedUser = repository.save(user);

		// Convert User Jpa entity into User Dto

//		UserDto savedUserDto = UserMapper.mapToUserDto(savedUser);
//		UserDto savedUserDto = modelMapper.map(savedUser, UserDto.class);
		UserDto savedUserDto = AutoUserMapper.MAPPER.mapToUserDto(savedUser);

		return savedUserDto;
	}

	@Override
	public UserDto getUserById(Long id) {
		User user = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
//		User savedUser = user.get();
//		return modelMapper.map(savedUser, UserDto.class);
		return AutoUserMapper.MAPPER.mapToUserDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users = repository.findAll();
//		return user.stream().map(UserMapper::mapToUserDto).collect(Collectors.toList());
//		return users.stream().map((user) -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
		return users.stream().map((user) -> AutoUserMapper.MAPPER.mapToUserDto(user)).collect(Collectors.toList());
	}

	@Override
	public UserDto updateUser(UserDto user) {
		User existingUser = repository.findById(user.getId())
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", user.getId()));
		existingUser.setName(user.getName());
		existingUser.setPhno(user.getPhno());
		existingUser.setEmail(user.getEmail());
		User updatedUser = repository.save(existingUser);
//		return UserMapper.mapToUserDto(updatedUser);
//		return modelMapper.map(updatedUser, UserDto.class);
		return AutoUserMapper.MAPPER.mapToUserDto(updatedUser);
	}

	@Override
	public void deleteUserById(Long id) {
		repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
		repository.deleteById(id);

	}

}
