package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.StateEntity;

public interface StateRepo extends JpaRepository<StateEntity, Long> {

	@Query(value ="select * from StateEntity where CountryId=:cId",nativeQuery =true)
	List<StateEntity>getStates(Long cId);
	
	
	StateEntity findByStateId(Long id);
}
