package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Enquiry;
import com.example.demo.repo.EnquiryRepo;

@Service
public class EnquiryServiceImplementation implements EnquiryServiceInterface {

	@Autowired
	private EnquiryRepo er;

	@Override
	public Boolean addEntity(Enquiry e) {
		return er.save(e).geteId() != null;
	}

	@Override
	public List<Enquiry> getEnquries() {
		return er.findAll();
	}

	@Override
    public List<Enquiry> filteredEnquiryList(String classMode, String course, String status) {
		//all are null
		if(classMode.isEmpty())classMode=null;
		if(course.isEmpty())course=null;
		if(status.isEmpty())status=null;
		
        if (classMode == null && course == null && status == null) {
            return er.findAll();
        }
        //two null values
        else if (classMode != null && course == null && status == null) {
            return er.findByClassMode(classMode);
        } else if (classMode == null && course != null && status == null) {
            return er.findByCourse(course);
        } else if (classMode == null && course == null && status != null) {
            return er.findByStatus(status);
        } 
        //one null value
        else if (classMode != null && course != null && status == null) {
            return er.findByClassModeAndCourse(classMode, course);
        } else if (classMode != null && course == null && status != null) {
            return er.findByClassModeAndStatus(classMode, status);
        } else if (classMode == null && course != null && status != null) {
            return er.findByCourseAndStatus(course, status);
        } else {
            return er.findByClassModeAndCourseAndStatus(classMode, course, status);
        }
    }

	@Override
	public Enquiry getEnquiryById(Long id) {
		return er.getByName(id);
	}


	@Override
	public Integer getAllRecords() {
		return er.totalRecords();
	}

	@Override
	public Integer getNewRecords() {
		return er.openRecords();
	}

	@Override
	public Integer getOldRecords() {
		return er.enrolledRecords();
	}

	@Override
	public Integer getLostRecords() {
		return er.lostRecords();
	}

}
