package com.inventory.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inventory.app.model.UserModel;
import com.inventory.app.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	@Transactional(readOnly = true)
	public Iterable<UserModel> finalAll() {
		return userRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<UserModel> finalAll(Pageable pageable) {
		return userRepository.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<UserModel> findById(Long id) {
		return userRepository.findById(id);
	}

	@Override
	@Transactional
	public UserModel save(UserModel user) {
		return userRepository.save(user);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		userRepository.deleteById(id);
	}
	
	
}
