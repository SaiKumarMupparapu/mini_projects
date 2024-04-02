package com.example.demo.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.example.demo.dto.Dashboard;
import com.example.demo.entity.Counsellor;
import com.example.demo.entity.Enquiry;
import com.example.demo.repo.CounsollerRepo;
import com.example.demo.repo.EnquiryRepo;
import com.example.demo.serviceInterface.EnquiryInterface;
@Service
public class EnquiryServiceImpl implements EnquiryInterface {

	@Autowired
	private EnquiryRepo erepo;

	@Autowired
	private CounsollerRepo crepo;

	@Override
	public Dashboard getDashboardDetails(Long CounselerId) {

		Long totalEnquries = erepo.getTotalEnquries(CounselerId);
		Long openEnq = erepo.getEnqCounts(CounselerId, "open");
		Long lostEnq = erepo.getEnqCounts(CounselerId, "lost");
		Long enrolledEnq = erepo.getEnqCounts(CounselerId, "enrolled");

		Dashboard d = new Dashboard();
		d.setTotalEnq(totalEnquries);
		d.setEnrolledEnq(enrolledEnq);
		d.setLostEnq(lostEnq);
		d.setOpenEnquiry(openEnq);
		return d;
	}

	@Override
	public Boolean saveEnquiry(Enquiry e, Long counsollerId) {
		Counsellor counsellor = crepo.findById(counsollerId).orElseThrow();
		// association mapping
		e.setCounsellor(counsellor);
		Enquiry save = erepo.save(e);
		return save.geteId() != null;
	}

	@Override
	public List<Enquiry> getEnquiries(Enquiry enq, Long counsellorId) {
		Counsellor counsellor = crepo.findById(counsellorId).orElseThrow();
		//association mapping
		enq.setCounsellor(counsellor);
		Example<Enquiry> of = Example.of(enq);
		List<Enquiry> list = erepo.findAll(of);
		return list;
	}

	@Override
	public Enquiry getEnquiryById(Long eId) {		
		return erepo.findById(eId).orElseThrow();
	}

}
