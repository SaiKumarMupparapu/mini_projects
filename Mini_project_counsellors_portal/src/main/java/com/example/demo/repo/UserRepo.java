package com.example.demo.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.Entity.User;

public interface UserRepo extends JpaRepository<User, Long> {
	
	Optional<User> findByEmail(String email);
	
	@Query(value = "select count(*) from User where email=:email",nativeQuery = true)
	Integer emailCheck(String email);
	
	
}
