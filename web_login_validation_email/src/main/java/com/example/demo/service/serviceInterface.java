package com.example.demo.service;

import com.example.demo.binding.User;

public interface serviceInterface {
	
	Boolean saveUser(User user);
	
	Boolean validateUser(String email,String pawwsord);
	
	Boolean resetPassword(String email,String password);

}
