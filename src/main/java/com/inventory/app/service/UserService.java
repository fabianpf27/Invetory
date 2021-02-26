package com.inventory.app.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.inventory.app.model.UserModel;

public interface UserService {

	
	public Iterable<UserModel> finalAll();
	
	public Page<UserModel> finalAll(Pageable pageable);
	
	public Optional<UserModel> findById(Long id);
	
	public UserModel save(UserModel user);
	
	public void deleteById(Long id);
	
}
