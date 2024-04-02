package com.example.demo.service;

import java.util.List;

import com.example.demo.Entity.Enquiry;

public interface EnquiryServiceInterface {
	
	// save enquiry
	Boolean addEntity(Enquiry e);
	
	//see all enquries
	List<Enquiry> getEnquries();
	
	List<Enquiry> filteredEnquiryList(String classMode,String course,String status);
	
	Enquiry getEnquiryById(Long id);
	Integer getAllRecords();
	Integer getNewRecords();
	Integer getOldRecords();
	Integer getLostRecords();
	
	
	

}
