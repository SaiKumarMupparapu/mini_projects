package com.example.demo.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Enquiry {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long eId;
	
	private String name;
	private String classMode;
	private String course;
	private String status;
	
	@CreationTimestamp
	@Column(updatable = false)
	private LocalDate createddate;
	
	@UpdateTimestamp
	@Column(insertable = false)
	private LocalDate updateddate;
	@ManyToOne
	@JoinColumn(name="CId")
	private Counsellor counsellor;
	
	public Long geteId() {
		return eId;
	}
	public void seteId(Long eId) {
		this.eId = eId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getClassMode() {
		return classMode;
	}
	public void setClassMode(String classMode) {
		this.classMode = classMode;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public LocalDate getCreateddate() {
		return createddate;
	}
	public void setCreateddate(LocalDate createddate) {
		this.createddate = createddate;
	}
	public LocalDate getUpdateddate() {
		return updateddate;
	}
	public void setUpdateddate(LocalDate updateddate) {
		this.updateddate = updateddate;
	}
	public Counsellor getCounsellor() {
		return counsellor;
	}
	public void setCounsellor(Counsellor counsellor) {
		this.counsellor = counsellor;
	}
	
	
}
