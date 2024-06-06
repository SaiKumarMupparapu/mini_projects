package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.CityEntity;

public interface CityRepo  extends JpaRepository<CityEntity, Long>{
	@Query(value="select * from CityEntity where stateId=:sId",nativeQuery = true)
	List<CityEntity> getCities(Long sId);
	
//	CityEntity findByCityName(String cityName);
	CityEntity findByCityId(Long id);

}
