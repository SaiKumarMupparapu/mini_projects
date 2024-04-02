package com.example.demo.serviceInterface;

import java.util.List;

import com.example.demo.dto.Dashboard;
import com.example.demo.entity.Enquiry;

public interface EnquiryInterface {

	Dashboard getBoardInfo(Long cId);
	Boolean saveEnquiry(Enquiry e,Long cId);
	List<Enquiry> getEnquries(Enquiry e,Long cId);
	Enquiry getEnquiryById(Long eId);
}
