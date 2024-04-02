package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.binding.User;

public interface UserRepo extends JpaRepository<User, Long> {
	
	User findByEmail(String email);
	
	@Query(value = "select count(*) from User where email=:email",nativeQuery = true)
	Integer emailCheck(String email);
	
	@Query(value = "update User set password=:password where email=:email",nativeQuery = true)
	void updatePassword(String email,String password);

}
