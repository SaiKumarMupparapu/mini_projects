package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.Entity.User;
import com.example.demo.repo.UserRepo;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements ServiceInterface {
	
	@Autowired
	private UserRepo ur;

	@Override
	public  Optional<User> validateLogin(String email, String password) {
		// ur.findByEmail(email).getPassword().equals(password)
		 return ur.findByEmail(email);
		
	}

	@Override
	public Boolean registerUser(User user) {
			User saved = ur.save(user);
			return saved.getId()!=null;
	}

	@Override
	public Integer checkMail(String email) {
		return ur.emailCheck(email);
	}
	

}