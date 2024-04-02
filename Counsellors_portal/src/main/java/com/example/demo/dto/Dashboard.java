package com.example.demo.dto;

public class Dashboard {

	private Long totalEnq;
	
	//open means new records
	private Long openEnquiry;
	
	private Long lostEnq;
	
	private Long enrolledEnq;

	public Long getTotalEnq() {
		return totalEnq;
	}

	public void setTotalEnq(Long totalEnq) {
		this.totalEnq = totalEnq;
	}

	public Long getOpenEnquiry() {
		return openEnquiry;
	}

	public void setOpenEnquiry(Long openEnquiry) {
		this.openEnquiry = openEnquiry;
	}

	public Long getLostEnq() {
		return lostEnq;
	}

	public void setLostEnq(Long lostEnq) {
		this.lostEnq = lostEnq;
	}

	public Long getEnrolledEnq() {
		return enrolledEnq;
	}

	public void setEnrolledEnq(Long enrolledEnq) {
		this.enrolledEnq = enrolledEnq;
	}
	
	
}
