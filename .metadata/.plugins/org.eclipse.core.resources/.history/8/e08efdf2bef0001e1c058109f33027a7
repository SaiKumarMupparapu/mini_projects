package com.example.demo.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Counsellor;
import com.example.demo.repo.CounsollerRepo;
import com.example.demo.serviceInterface.CounsellorInterface;

@Service
public class CounsellorImpl implements CounsellorInterface{
	
	@Autowired
	private CounsollerRepo crepo;

	@Override
	public Boolean registerCounsellor(Counsellor c) {
		Counsellor byEmail = crepo.findByEmail(c.getEmail());
		if(byEmail!=null) {
			return false;
		}else {
		Counsellor save = crepo.save(c);		
		return save.getCId()!=null;
		}
	}

	@Override
	public Counsellor validateLogin(String email, String pswd) {
		return crepo.findByEmailAndPswd(email, pswd);
		  
	}

	

}
