package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.binding.User;
import com.example.demo.email.util.EmailUtil;
import com.example.demo.repo.UserRepo;
@Service
public class UserServiceImple implements serviceInterface {
	
	@Autowired
	private UserRepo ur;
	@Autowired
	private EmailUtil eu;
	
	public Integer ckeckMail(String email) {
		return ur.emailCheck(email);
	}

	@Override
	public Boolean saveUser(User user) {		
		User saved = ur.save(user);
		
		String to=saved.getEmail();
		String subj="Account created sucessfully";
		String body="Thanks for registring in our wesite";
		
		eu.sendMail( to, subj, body);
		return saved.getId() != null;

	}

	@Override
	public Boolean validateUser(String email, String pawwsord) {
		return ur.findByEmail(email).getPassword().equals(pawwsord);
	}

	@Override
	public Boolean resetPassword(String email, String password) {
		ur.updatePassword(email, password);
		
		return true;
	}
	

}
