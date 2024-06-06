package com.example.demo.service;

import java.util.Map;

import com.example.demo.dto.LoginDto;
import com.example.demo.dto.RegisterDto;
import com.example.demo.dto.ResetDto;
import com.example.demo.dto.UserDto;

public interface UserService {
	public Map<Long,String>getCountries();

	public Map<Long,String>getStatesw(Long cId);

	public Map<Long,String>getCities(Long cId);
	
	public UserDto getUser(String email);
	
	public Boolean registerUser(RegisterDto rd);

	public UserDto loginValidate(LoginDto ld);
	
	public Boolean resetPaswd(ResetDto rd);
	
	public String getQuote();
	
	
	
	
}
