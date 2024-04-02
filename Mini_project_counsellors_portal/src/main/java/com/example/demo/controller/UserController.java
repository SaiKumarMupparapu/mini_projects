package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.demo.Entity.User;
import com.example.demo.service.UserServiceImpl;

@Controller
@SessionAttributes({"tot","newRecord","old","lost"})
public class UserController {
	@Autowired
	private UserServiceImpl us;

	@GetMapping("/register")
	public String registerPage(Model model) {
		model.addAttribute("user", new User());
		return "registration";
	}

	@PostMapping("/register")
	public String handleRegister(Model model, User user) {
		Integer checkMail = us.checkMail(user.getEmail());
		if (checkMail == 0) {
			Boolean saved = us.registerUser(user);
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
	
	// login page get and post methods
	
	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("user", new User());
		return "login";
	}

	@PostMapping("/login")
	public String handleLogin(Model model, User user) {
		
		 Optional<User> validateLogin = us.validateLogin(user.getEmail(), user.getPassword());
		if (validateLogin.isPresent()) {
			User user2 = validateLogin.get();
			if(user2.getPassword().equals(user.getPassword())) {
				return "redirect:/dashboard";
			}else {
				model.addAttribute("msg", "Invalid credentials");
				return "login";
			}
		}else {
			model.addAttribute("msg", "No account found with this email");
			return "login";
		}
	}
	@GetMapping("/dashboard")
	public String dashboard(Model model) {
	
		model.addAttribute("tot", model.getAttribute("tot"));
		model.addAttribute("newRecord", model.getAttribute("new"));
		model.addAttribute("old", model.getAttribute("old"));
		model.addAttribute("lost", model.getAttribute("lost"));
		return "home";
	}

}
