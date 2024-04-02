package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.binding.User;
import com.example.demo.service.UserServiceImple;

@Controller
public class UserController {
	@Autowired
	private UserServiceImple us;

	@GetMapping("/register")
	public String registerPage(Model model) {
		model.addAttribute("user", new User());
		return "registration";
	}

	@PostMapping("/register")
	public String handleRegister(Model model, User user) {
		Integer ckeckMail = us.ckeckMail(user.getEmail());
		if (ckeckMail <= 1) {
			Boolean saved = us.saveUser(user);
			if (saved)
				return "login";
			else {
				model.addAttribute("msg", "Unable to save! try again");
				return "registration";
			}
		} else {
			model.addAttribute("msg", "This mail is alredy registred try another mail");
			return "registration";
		}
	}

	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("user", new User());
		return "login";
	}

	@PostMapping("/login")
	public String handleLogin(Model model, User user) {
		Boolean validateUser = us.validateUser(user.getEmail(), user.getPassword());
		if (validateUser)
			return "home";
		else {
			model.addAttribute("msg", "Invalid credentials");
			return "login";
		}
	}

	@GetMapping("/home")
	public String homePage(Model model) {

		return "home";
	}
	
	@GetMapping("/reset")
	public String resetPassword(Model model) {
		model.addAttribute("user", new User());		
		return"resetpage";
	}
	
	@PostMapping("/reset")
	public String resetPasswordHandeler(Model model,User user) {
		
		Boolean resetPassword = us.resetPassword(user.getEmail(), user.getPassword());
		if(resetPassword) {
			model.addAttribute("msg","Password update success" );
		}else {
			model.addAttribute("msg","Try again" );
		}
		
		return"resetpage";
	}
}
