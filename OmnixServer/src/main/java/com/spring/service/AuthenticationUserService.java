package com.spring.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.spring.entity.AuthenticationUser;
import com.spring.repository.AuthenticationUserRepository;

@Service
public class AuthenticationUserService {
	@Autowired
    AuthenticationUserRepository authenticationUserRepository;

    
    public AuthenticationUser login(String userName, String password) {
        Optional<AuthenticationUser> authUser = authenticationUserRepository.login(userName,password);
        AuthenticationUser user = null;
        if(authUser.isPresent()){
            String token = UUID.randomUUID().toString();
             user = authUser.get();
            user.setToken(token);
            authenticationUserRepository.save(user);
        }else {
        	user = new AuthenticationUser(userName,password);
        	user.setToken("unauthorized");
        }

        return user;
    }

   
    public Optional<User> findByToken(String token) {
        Optional<AuthenticationUser> customer= authenticationUserRepository.findByToken(token);
        if(customer.isPresent()){
        	AuthenticationUser AuthenticatedUser = customer.get();
        	User user= new User(AuthenticatedUser.getUserName(), AuthenticatedUser.getPassword(), true, true, true, true,
                    AuthorityUtils.createAuthorityList("USER"));
            return Optional.of(user);
        }
        return  Optional.empty();
    }
    
    public List<AuthenticationUser> findAll(){
    	return authenticationUserRepository.findAll();
    }
}

