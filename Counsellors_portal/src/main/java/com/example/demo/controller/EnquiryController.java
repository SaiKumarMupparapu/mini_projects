package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.dto.Dashboard;
import com.example.demo.entity.Enquiry;
import com.example.demo.serviceImpl.EnquiryServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class EnquiryController {
	@Autowired
	private EnquiryServiceImpl es;
	
	
	
	//add enquiry
	@GetMapping("/add")
	public String addEnquiry(Model model) {
		model.addAttribute("enq",new Enquiry());
		return"enquiry";
	}
	
	@PostMapping("/add")
	public String addEnquiryHandeler(Model model,Enquiry e,HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		Long cid=(Long)session.getAttribute("CId");
		Boolean saved = es.saveEnquiry(e, cid);
		if(saved) {
			model.addAttribute("smsg","Enquiry added");
		}else {
			model.addAttribute("emsg","Enquiry not added");
		}
		model.addAttribute("enq",new Enquiry());
		return"enquiry";
	}
	
	
	// view enquiries
	@GetMapping("/view")
	public String viewEnquiry(Model model,HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		Long cId=(Long)session.getAttribute("CId");
		List<Enquiry> enquries = es.getEnquries(new Enquiry(), cId);
		model.addAttribute("list", enquries);
		// it is for filteraction purpose
		model.addAttribute("enq",new Enquiry());
		return"list";
	}
	//filter enquries
	
	@PostMapping("/filter")
	public String filterEnquries(Model model,@ModelAttribute("enq") Enquiry e,HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		Long cId=(Long)session.getAttribute("CId");
		
		List<Enquiry> enquries = es.getEnquries(e, cId);
		model.addAttribute("list", enquries);
		return"list";
	}
	
	//update
	@GetMapping("/edit/{id}")
	public String edit(Model model,@PathVariable("id") Long id) {
		Enquiry enquiry = es.getEnquiryById(id);
		model.addAttribute("enq", enquiry);
		return"enquiry";
	}
	@GetMapping("/dashboard")
	public String dashBoardInfo(Model model, HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		Long cId=(Long)session.getAttribute("CId");		
		Dashboard boardInfo = es.getBoardInfo(cId);
		model.addAttribute("dashboardInfo", boardInfo);
		return"dashboard";
	}
	

}
