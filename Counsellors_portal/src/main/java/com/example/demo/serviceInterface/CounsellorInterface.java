package com.example.demo.serviceInterface;

import com.example.demo.entity.Counsellor;

public interface CounsellorInterface {
	
	Boolean saveCounsellor(Counsellor c);
	
	Counsellor loginValidate(String email,String pswd);
	Boolean getByEmail(String email);
	
	

}
