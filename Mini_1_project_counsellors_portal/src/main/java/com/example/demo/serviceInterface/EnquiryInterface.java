package com.example.demo.serviceInterface;

import java.util.List;

import com.example.demo.dto.Dashboard;
import com.example.demo.entity.Enquiry;

public interface EnquiryInterface {
	
	public Dashboard getDashboardDetails(Long CounselerId);
	
	public Boolean saveEnquiry(Enquiry e,Long counsollerId);
	
	public List<Enquiry> getEnquiries(Enquiry enq,Long counsellorId);
	
	public Enquiry getEnquiryById(Long eId);

}