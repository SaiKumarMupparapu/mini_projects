package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Enquiry;
import com.example.demo.serviceImpl.EnquiryServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class EnquiryController {
	@Autowired
	private EnquiryServiceImpl eService;

	// 1 ==> add enq & save enquiry
	
	@GetMapping("/add")
	public String addEnquiry(Model model) {
		model.addAttribute("enqObj",new Enquiry());
		return "addEnquiry";
	}
	
	@PostMapping("/addEnq")
	public String addEnquiryHandeler(Model model,Enquiry enq,HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		 Long cId=(Long)session.getAttribute("CId");
		 
		 Boolean saved = eService.saveEnquiry(enq, cId);
		 if(saved) {
			 model.addAttribute("smsg","Enquiry added ");
		 }
		 else {
			 model.addAttribute("emsg", "try again");
		 }
		return "addEnquiry";
	}
	
	
	// view 
	@GetMapping("/view")
	public String viewEnquries(Model model,HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		Long cId=	(Long)session.getAttribute("CId");
		
		List<Enquiry> list = eService.getEnquiries(new Enquiry(), cId);
		model.addAttribute("list", list);
		
		// for filtering binding the enquire object
		
		model.addAttribute("enqObj",new Enquiry());
		return "EnquiryList";
	}
	
	//filter
	@PostMapping("/filter")
	public String filterEnquiry(Model model,HttpServletRequest req,Enquiry enq) {
		HttpSession session = req.getSession(false);
		Long cI=(Long)session.getAttribute("CId");
		List<Enquiry> enquiries = eService.getEnquiries(enq, cI);
		model.addAttribute("list", enquiries);
		return "EnquiryList";
	}
	
	//edit/update enq
	@GetMapping("/update/id")
	public String editEnquiry(Model model,@PathVariable("id")Long eId) {
		Enquiry enquiryById = eService.getEnquiryById(eId);
		model.addAttribute("enq", enquiryById);
		return"addEnquiry";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
