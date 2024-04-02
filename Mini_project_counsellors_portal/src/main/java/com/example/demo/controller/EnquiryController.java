package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Entity.Enquiry;
import com.example.demo.service.EnquiryServiceImplementation;

@Controller
public class EnquiryController {

	@Autowired
	private EnquiryServiceImplementation es;

	@GetMapping("/enquiry")
	public String enquiryPage(Model model) {
		model.addAttribute("enquiry", new Enquiry());
		return "enquiry";
	}

	@PostMapping("/enquiry")
	public String handleEnquiryPage(Model model, Enquiry enquiry) {
		Boolean status = es.addEntity(enquiry);
		if (status) {
			model.addAttribute("smsg", "eniquiry added successfully");
		} else {
			model.addAttribute("emsg", "unable to add eniquiry !try again");
		}
		return "enquiry";
	}

	@GetMapping("/viewenquiry")
	public String ViewEnquiries(Model model) {
		List<Enquiry> list = es.getEnquries();
		model.addAttribute("list", list);
		return "enquirylist";
	}

	@GetMapping("/filter")
	public String filterEnquiryData(@RequestParam(value="classMode",required=false) String classMode, @RequestParam(value="course",required = false) String course,
			@RequestParam(value="status",required = false) String status, Model model) {
		model.addAttribute("list",es.filteredEnquiryList(classMode, course, status));
		return "enquirylist";
	}
	@GetMapping("/update/{id}")
	public String modify(Model model,@PathVariable("id")Long id) {
		
		model.addAttribute("enquiry",es.getEnquiryById(id));
		return "edit";
	}
	@PostMapping("/update")
	public String handleModify(Model model,Enquiry e) {
		Boolean updated = es.addEntity(e);
		if(updated)model.addAttribute("smsg","Succefully updated");
		else model.addAttribute("emsg", "Succefully updated");
		
		return"edit";
	}

	@PostMapping("/enqDetails")
	public String enquiryDetails(Model model) {
		model.addAttribute("tot",es.getAllRecords());
		model.addAttribute("newRecord", es.getNewRecords());
		model.addAttribute("old", es.getOldRecords());
		model.addAttribute("lost", es.getLostRecords());
		
		model.mergeAttributes(model.asMap());
			
		return "redirect:/dashboard";
	}

}