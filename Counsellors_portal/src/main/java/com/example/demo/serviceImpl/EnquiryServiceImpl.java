package com.example.demo.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.example.demo.dto.Dashboard;
import com.example.demo.entity.Counsellor;
import com.example.demo.entity.Enquiry;
import com.example.demo.repo.CounselloerRepo;
import com.example.demo.repo.EnquiryRepo;
import com.example.demo.serviceInterface.EnquiryInterface;
@Service
public class EnquiryServiceImpl implements EnquiryInterface {

	@Autowired
	private EnquiryRepo er;
	@Autowired
	private CounselloerRepo cr;
	
	
	
	@Override
	public Dashboard getBoardInfo(Long cId) {
		Long totalEnquries = er.getTotalEnquries(cId);
		Long openEnq = er.getEnqCounts(cId,"New");
		Long enrolled = er.getEnqCounts(cId, "Enrolled");
		Long lost = er.getEnqCounts(cId, "Lost");
		
		Dashboard d= new Dashboard();
		d.setTotalEnq(totalEnquries);
		d.setEnrolledEnq(enrolled);
		d.setOpenEnquiry(openEnq);
		d.setLostEnq(lost);
		
		return d;
	}

	@Override
	public Boolean saveEnquiry(Enquiry e, Long cId) {
		Counsellor counsellor = cr.findById(cId).orElseThrow();
		e.setCounsellor(counsellor);
		Enquiry save = er.save(e);
		return save.geteId()!=null;
	}

	@Override
	public List<Enquiry> getEnquries(Enquiry e, Long cId) {
		Counsellor coun=new Counsellor();
		coun.setCId(cId);
		Enquiry enquiry = new Enquiry();
		enquiry.setCounsellor(coun);
		if(null!=e.getClassMode() && !"".equals(e.getClassMode())) {
			enquiry.setClassMode(e.getClassMode());		
		}
		if(null!=e.getCourse() && !"".equals(e.getCourse())) {
			enquiry.setCourse(e.getCourse());
		}
		if(null!=e.getStatus() && !"".equals(e.getStatus())) {
			enquiry.setStatus(e.getStatus());			
		}
		
		
		Example<Enquiry> of = Example.of(enquiry);
		List<Enquiry> list = er.findAll(of);
		return list;
	}

	@Override
	public Enquiry getEnquiryById(Long eId) {
		return er.findById(eId).orElseThrow();
	}

	

}


















