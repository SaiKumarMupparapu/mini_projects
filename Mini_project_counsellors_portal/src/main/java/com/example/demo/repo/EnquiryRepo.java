package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.Entity.Enquiry;

public interface EnquiryRepo extends JpaRepository<Enquiry, Long> {
	
	@Query(value = "select count(*) from enquiry_tbl",nativeQuery = true)
	Integer totalRecords();
	
	@Query(value = "select count(*) from enquiry_tbl where status='new'",nativeQuery = true)
	Integer openRecords();

	@Query(value = "select count(*) from enquiry_tbl where status='old'",nativeQuery = true)
	Integer enrolledRecords();

	@Query(value = "select count(*) from enquiry_tbl where status='lost'",nativeQuery = true)
	Integer lostRecords();
	
	
	
	List<Enquiry> findByClassMode(String classMode);
	List<Enquiry> findByCourse(String course);
	List<Enquiry> findByStatus(String status);
	
	List<Enquiry> findByCourseAndStatus(String course, String status);
	List<Enquiry> findByClassModeAndStatus(String classMode, String status);
	List<Enquiry> findByClassModeAndCourse(String classMode, String course);
	
	
	List<Enquiry> findByclassModeAndCourse(String classMode, String course);
	
	List<Enquiry> findByClassModeAndCourseAndStatus(String classMode, String course, String status);
	
	@Query("from Enquiry where eId=:id")
	Enquiry getByName(Long id);
	

}
