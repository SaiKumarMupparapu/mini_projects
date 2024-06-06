package com.example.demo.dto;

public class ResetDto {
	
	
	private String email;
	private String oldPswd;
	private String newPswd;
	private String confirmPswd;
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getOldPswd() {
		return oldPswd;
	}
	public void setOldPswd(String oldPswd) {
		this.oldPswd = oldPswd;
	}
	public String getNewPswd() {
		return newPswd;
	}
	public void setNewPswd(String newPswd) {
		this.newPswd = newPswd;
	}
	public String getConfirmPswd() {
		return confirmPswd;
	}
	public void setConfirmPswd(String confirmPswd) {
		this.confirmPswd = confirmPswd;
	}
	
	
	

}
