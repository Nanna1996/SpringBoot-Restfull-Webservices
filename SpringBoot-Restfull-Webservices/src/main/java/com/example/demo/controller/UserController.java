package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserDto;
import com.example.demo.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Tag(name = "CRUD REST API's For User Resources", description = "CRUD REST API's For\n"
		+ "Create User,Get user by id, Get all User's,Update User,Delete User")
@RestController
@RequiredArgsConstructor
public class UserController {

	private final @NonNull UserService service;
	private static final String USER_NOT_FOUND = "User is Not available";

	// http://localhost:2024/create

	@Operation(summary = "CREATE USER REST API", description = " Used to create new Resource in Rest Api")
	@ApiResponse(responseCode = "201", description = "HTTP Status 201 Created")
	@PostMapping("/create")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto user) {

		UserDto savedUser = service.saveUser(user);
		return new ResponseEntity<UserDto>(savedUser, HttpStatus.CREATED);
	}

	// http://localhost:2024/getById/2

	@Operation(summary = "GET USER REST API", description = "To get the USER by Id")
	@ApiResponse(responseCode = "200", description = "HTTP Status 200 Success")
	@GetMapping("getById/{id}")
	public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
		return new ResponseEntity<UserDto>(service.getUserById(id), HttpStatus.OK);
	}

	// http://localhost:2024/getAll

	@Operation(summary = "GET ALL USERS REST API", description = "To get the all Users in DB")
	@ApiResponse(responseCode = "200", description = "HTTP Status 200 Success")
	@GetMapping("/getAll")
	public ResponseEntity<List<UserDto>> getAllUsers() {
		return new ResponseEntity<List<UserDto>>(service.getAllUsers(), HttpStatus.OK);
	}

	// http://localhost:2024/update/3

	@Operation(summary = "UPDATE REST API", description = " To Update the existing user")
	@ApiResponse(responseCode = "200", description = "HTTP Status 200 Success")
	@PutMapping("/update/{id}")
	public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody @Valid UserDto user) {
		user.setId(id);
		return new ResponseEntity<UserDto>(service.updateUser(user), HttpStatus.OK);
	}

	// http://localhost:2024/delete/3

	@Operation(summary = "DELETE REST API", description = "To delete the existing user")
	@ApiResponse(responseCode = "200", description = "HTTP Status 200 Success")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteUserById(@PathVariable Long id) {
		service.deleteUserById(id);
		return new ResponseEntity<String>("User deleted Successfully!", HttpStatus.OK);
	}

//	@ExceptionHandler(ResourceNotFoundException.class)
//	public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException exception,
//			WebRequest request) {
//		ErrorDetails details = new ErrorDetails(LocalDateTime.now(), exception.getMessage(),
//				request.getDescription(false), USER_NOT_FOUND);
//		return new ResponseEntity<ErrorDetails>(details, HttpStatus.NOT_FOUND);
//
//	}

}
