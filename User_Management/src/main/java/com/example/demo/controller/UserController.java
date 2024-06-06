package com.example.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dto.LoginDto;
import com.example.demo.dto.RegisterDto;
import com.example.demo.dto.ResetDto;
import com.example.demo.dto.UserDto;
import com.example.demo.service.UserServiceImpl;

@Controller
public class UserController {
	@Autowired
	private UserServiceImpl userService;

	// register
	@GetMapping("/register")
	public String registerPage(Model model) {
		model.addAttribute("registerDto", new RegisterDto());
		model.addAttribute("countries", userService.getCountries());
		return "register";
	}

	// states
	@GetMapping("/states/{id}")
	@ResponseBody
	public Map<Long, String> getStates(@PathVariable("id") Long cId) {
		Map<Long, String> states = userService.getStatesw(cId);
		return states;

	}

	// cities
	@GetMapping("/cities/{id}")
	@ResponseBody
	public Map<Long, String> getCities(@PathVariable("id") Long sId) {
		return userService.getCities(sId);
	}

	@PostMapping("/register")
	public String registerHandler(Model model, RegisterDto rd) {
		UserDto user = userService.getUser(rd.getEmail());
		if (user != null) {
			model.addAttribute("emsg", "this email is alredy registred");
		} else {
			Boolean registeredUser = userService.registerUser(rd);
			if (registeredUser)
				model.addAttribute("smsg", "Registration success");
			else
				model.addAttribute("emsg", "Registration Failed");
		}
		return "register";
	}

	// login
	@GetMapping("/login")
	public String loginPage(Model model) {
		model.addAttribute("loginDto", new LoginDto());
		return "login";
	}

	@PostMapping("/login")
	public String loginHandler(Model model, LoginDto ld) {
		UserDto user = userService.loginValidate(ld);
		if (user == null) {
			model.addAttribute("emsg", "Invalid credentials");
			return "login";
		} else {
			String updatedPswd = user.getUpdatedPswd();
			if ("YES".equals(updatedPswd)) {
				return "redirect:dashboard";
			} else {
				ResetDto resetDto = new ResetDto();
				resetDto.setEmail(user.getEmail());
				model.addAttribute("restDto", resetDto);
				return "reset";
			}

		}
	}

	// reset password
	@PostMapping("/reset")
	public String resetPassword(Model model,@ModelAttribute("restDto") ResetDto rd) {
		if (!(rd.getNewPswd().equals(rd.getConfirmPswd()))) {
			model.addAttribute("emsg", "Confirm password and new password are not maching");
			return "reset";
		} else {
			UserDto user = userService.getUser(rd.getEmail());
			if (user.getPswd().equals(rd.getOldPswd())) {
				Boolean resetPaswd = userService.resetPaswd(rd);
				if (resetPaswd) {
					return "redirect:dashboard";
				} else {
					model.addAttribute("emsg", "updating password failed");
					return "reset";
				}

			} else {
				model.addAttribute("emsg", "invalid Old password");
				return "reset";
			}
		}

	}

	// dashboard
	@GetMapping("/dashboard")
	public String dashboard(Model model) {
		String quote = userService.getQuote();
		model.addAttribute("quote", quote);
		return "dashboard";
	}

	// logout
	@GetMapping("/logout")
	public String logout() {
		return "redirect:login";
	}

}
