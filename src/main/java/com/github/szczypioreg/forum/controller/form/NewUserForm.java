/**
 * Created by Dawid Stankiewicz on 28.07.2016
 */
package com.github.szczypioreg.forum.controller.form;

public class NewUserForm {
	
	private String email;
	
	private String username;
	
	private String password;
	
	public NewUserForm() {}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
