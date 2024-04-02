package com.example.demo.serviceInterface;

import com.example.demo.entity.Counsellor;

public interface CounsellorInterface {
	
	public Boolean registerCounsellor(Counsellor c);
	public Counsellor validateLogin(String email,String pswd);

}
