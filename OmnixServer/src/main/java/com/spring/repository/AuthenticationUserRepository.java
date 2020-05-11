package com.spring.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.spring.entity.AuthenticationUser;

@Repository
public interface AuthenticationUserRepository extends CrudRepository<AuthenticationUser, Integer> {
	 
    @Query(value = "SELECT * FROM authentication_user WHERE user_name = ?1 AND password = ?2 ",nativeQuery = true)
    Optional<AuthenticationUser> login(String username,String password);
    Optional<AuthenticationUser> findByToken(String token);
    List<AuthenticationUser> findAll();
    
}