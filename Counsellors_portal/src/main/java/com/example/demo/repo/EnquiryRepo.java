package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.Enquiry;

public interface EnquiryRepo extends JpaRepository<Enquiry,Long> {
	@Query(value="select count(*) from Enquiry where cid=:CId",nativeQuery = true)
	Long getTotalEnquries(Long CId);
	
	@Query(value="select count(*) from Enquiry where cid=:CId and status=:status",nativeQuery = true)
	Long getEnqCounts(Long CId,String status);

}
