package com.example.demo.entity;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Counsellor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long CId;
	private String name;
	private String email;
	private Long phno;
	private String pswd;

	@CreationTimestamp
	@Column(updatable = false)
	private LocalDate creationDate;

	@UpdateTimestamp
	@Column(insertable = false)
	private LocalDate updateDate;

	@OneToMany(mappedBy = "counsellor", cascade = CascadeType.ALL)
	private List<Enquiry> enquries;

	public Long getCId() {
		return CId;
	}

	public void setCId(Long cId) {
		CId = cId;
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

	public LocalDate getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}

	public LocalDate getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(LocalDate updateDate) {
		this.updateDate = updateDate;
	}

	public List<Enquiry> getEnquries() {
		return enquries;
	}

	public void setEnquries(List<Enquiry> enquries) {
		this.enquries = enquries;
	}

}
