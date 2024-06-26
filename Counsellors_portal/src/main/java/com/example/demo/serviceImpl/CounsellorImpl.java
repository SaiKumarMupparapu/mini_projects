package com.example.demo.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Counsellor;
import com.example.demo.repo.CounselloerRepo;
import com.example.demo.serviceInterface.CounsellorInterface;

@Service
public class CounsellorImpl implements CounsellorInterface{
	@Autowired
	private CounselloerRepo cr;

	@Override
	public Boolean saveCounsellor(Counsellor c) {
			Counsellor save = cr.save(c);
			return save.getCId()!=null;		
	}

	@Override
	public Counsellor loginValidate(String email, String pswd) {
		Counsellor byEmailAndPswd = cr.findByEmailAndPswd(email, pswd);
		return byEmailAndPswd;
	}

	@Override
	public Boolean getByEmail(String email) {
		Counsellor byEmail = cr.findByEmail(email);
		return byEmail==null;
	}

	

	

}
