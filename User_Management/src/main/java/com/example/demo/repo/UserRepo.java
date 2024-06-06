package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.UserEntity;


public interface UserRepo extends JpaRepository<UserEntity, Long>{
	
	UserEntity findByEmail(String email);
	@Query(value = "select * from user_entity where email=:email and pswd=:pswd",nativeQuery = true)
	UserEntity findByEmailAndPswd(String email, String pswd);

}
