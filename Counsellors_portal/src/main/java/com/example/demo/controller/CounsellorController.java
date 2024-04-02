package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.dto.Dashboard;
import com.example.demo.entity.Counsellor;
import com.example.demo.serviceImpl.CounsellorImpl;
import com.example.demo.serviceImpl.EnquiryServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class CounsellorController {
	@Autowired
	private CounsellorImpl cs;
	@Autowired
	private EnquiryServiceImpl es;
	
	//registration 
	@GetMapping("/register")
	public String registration(Model model) {
		model.addAttribute("counObj", new Counsellor());
		return "registration";
	}
	@PostMapping("/register")
	public String registrationHandeler(Model model,Counsellor c) {
		Boolean byEmail = cs.getByEmail(c.getEmail());
		if(byEmail) {
			Boolean saveCounsellor = cs.saveCounsellor(c);
			if(saveCounsellor) {
				return"login";
				
			}else {
				model.addAttribute("emsg", "Try again");
			}
			
		}else {
			
			model.addAttribute("emsg", "Email alredy registred");
			model.addAttribute("counObj",new Counsellor());
		}
		return "registration";
	}
	
	//login
	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("counsellor", new Counsellor());
		return "login";
	}
	
	@PostMapping("/login")
	public String loginHandeler(Model model,Counsellor c,HttpServletRequest req) {
		Counsellor loginValidate = cs.loginValidate(c.getEmail(), c.getPswd());
		if(loginValidate!=null) {
			HttpSession session = req.getSession(true);
			session.setAttribute("CId",loginValidate.getCId());
			
			Dashboard boardInfo = es.getBoardInfo(loginValidate.getCId());
			model.addAttribute("dashboardInfo", boardInfo);
			return"dashboard";
		}else {
			model.addAttribute("emsg", "Invalid credntials");
			return"login";
		}
		
		
	}
			
	@GetMapping("/logout")
	public String logout(Model model,HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		session.invalidate();		
		return"redirect:/login";
	}

	
}
