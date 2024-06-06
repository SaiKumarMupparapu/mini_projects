package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.CountryEntity;


public interface CountryRepo extends JpaRepository<CountryEntity, Long>{
	
	CountryEntity findByCountryId(Long id);

}
