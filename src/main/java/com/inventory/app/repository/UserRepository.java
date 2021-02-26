package com.inventory.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inventory.app.model.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

	
	
}
