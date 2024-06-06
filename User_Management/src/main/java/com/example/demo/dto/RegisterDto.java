package com.example.demo.dto;

public class RegisterDto {
	private String name;
	private String email;
	private Long phno;
	private Long countryId;
	private Long stateId;
	private Long cityId;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getPhno() {
		return phno;
	}
	public void setPhno(Long phno) {
		this.phno = phno;
	}
	public Long getCountryId() {
		return countryId;
	}
	public void setCountry(Long countryId) {
		this.countryId = countryId;
	}
	public Long getStateId() {
		return stateId;
	}
	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}
	public Long getCityId() {
		return cityId;
	}
	public void setCity(Long cityId) {
		this.cityId = cityId;
	}
	
	

}
