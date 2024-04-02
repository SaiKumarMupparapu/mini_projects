package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Counsellor;


public interface CounsollerRepo extends JpaRepository<Counsellor, Long> {
	
	
	
//	Counsellor findByCId(Long Cid);
	Counsellor findByEmail(String email);
	Counsellor  findByEmailAndPswd(String email, String pswd);

}