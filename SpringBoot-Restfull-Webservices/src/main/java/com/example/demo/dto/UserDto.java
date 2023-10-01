package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "UserDto model Information")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

	private Long id;
	// name should not be null or empty
	@NotEmpty(message = "User name should not be Empty")
	private String name;
//	@NotEmpty(message = "User Phone Number should be Empty")
	private long phno;
	// email should not be null or empty and
	// email should be valid
	@NotEmpty(message = "Email should not be empty")
	@Email(message = "Email address should be valid")
	private String email;

}
