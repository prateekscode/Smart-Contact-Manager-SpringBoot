package com.scm.forms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserForm {
	@NotBlank(message = "Username is required")
	@Size(min = 3, message = "Min 3 Characters is required")
	private String name;
	@NotBlank(message = "Email is required")
	@Email(message = "Invalid Email Address")
	private String email;
	@NotBlank(message = "Password is required")
	@Size(min = 6, message = "Min 6 Characters is required")
	private String password;
	@NotBlank
	private String about;
	@Size(min = 10, max = 12, message = "Invalid Phone Number")
	private String phoneNumber;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public String toString() {
		return "UserForm [name=" + name + ", email=" + email + ", password=" + password + ", about=" + about
				+ ", phoneNumber=" + phoneNumber + "]";
	}

}
