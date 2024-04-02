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
	private CounsellorImpl counService;
	@Autowired
	private EnquiryServiceImpl eService;

	@GetMapping("/registration")
	public String registerPage(Model model) {
		model.addAttribute("couObj",new Counsellor());
		return"registration";
	}
	
	@PostMapping("/registration")
	public String HandleRegister(Model model,Counsellor c) {
		Boolean status = counService.registerCounsellor(c);
		if(status) {			
			return"login";
		}else {
			model.addAttribute("msg","unable to register try again");
			return"registration";
		}
		
	}
	
	@GetMapping("/login")
	public String loginPage(Model model) {
		model.addAttribute("couObj",new Counsellor());
		return"login";
	}
	
	@PostMapping("/login")
	public String loginHandler(Model model,Counsellor c,HttpServletRequest req) {
		 Counsellor counsellor = counService.validateLogin(c.getEmail(),c.getPswd());
		if(counsellor!=null) {		
			
			HttpSession session = req.getSession(true);
			session.setAttribute("CId",counsellor.getCId() );
			
			Dashboard dashboardDetails = eService.getDashboardDetails(c.getCId());
			model.addAttribute("dashboard", dashboardDetails);
			return"dashboard";
		}else {
			model.addAttribute("msg","Invalid credentials");
			return"login";
		}
		
	}
	@GetMapping("/logout")
	public String logout(HttpServletRequest req,Model model) {
		HttpSession session = req.getSession(false);
		session.invalidate();
		return "redirect:/login";
	}
}

