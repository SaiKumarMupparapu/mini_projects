package com.example.demo.dto;

public class UserDto {
	
	private Long userId;
	private String name;
	private String email;
	private Long phno;
	private String pswd;
	private String updatedPswd;
	private String newPswd;
	private String ConfirmPswd;
	private Long countryId;
	private Long stateId;
	private Long cityId;
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
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
	public String getPswd() {
		return pswd;
	}
	public void setPswd(String pswd) {
		this.pswd = pswd;
	}
	public String getUpdatedPswd() {
		return updatedPswd;
	}
	public void setUpdatedPswd(String updatedPswd) {
		this.updatedPswd = updatedPswd;
	}
	public String getNewPswd() {
		return newPswd;
	}
	public void setNewPswd(String newPswd) {
		this.newPswd = newPswd;
	}
	public String getConfirmPswd() {
		return ConfirmPswd;
	}
	public void setConfirmPswd(String confirmPswd) {
		ConfirmPswd = confirmPswd;
	}
	public Long getCountryId() {
		return countryId;
	}
	public void setCountryId(Long countryId) {
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
	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}
	
	
	

}
