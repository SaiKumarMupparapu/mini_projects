package com.example.demo.service;

import java.util.Optional;

import com.example.demo.Entity.User;

public interface ServiceInterface {
	
	public  Optional<User> validateLogin(String email,String password);
	
	public Boolean registerUser(User user);
	
	public Integer checkMail(String email);

}
