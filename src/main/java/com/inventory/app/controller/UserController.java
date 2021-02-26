package com.inventory.app.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inventory.app.model.UserModel;
import com.inventory.app.service.UserService;


@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseEntity<?> create (@RequestBody UserModel user){
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> read(@PathVariable Long id){
		Optional<UserModel> oUser = userService.findById(id);
		
		if(!oUser.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.of(oUser);		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update (@RequestBody UserModel userDetails, @PathVariable Long id){
		Optional<UserModel> user = userService.findById(id);
		
		if(!user.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		user.get().setNombre(userDetails.getNombre());
		user.get().setEmail(userDetails.getEmail());
		user.get().setPrioridad(userDetails.getPrioridad());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user.get()));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		
		if(!userService.findById(id).isPresent()) {
			return ResponseEntity.notFound().build();
		}		
		
		userService.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping
	public List<UserModel> readAll(){
		
		List<UserModel> users = StreamSupport
								.stream(userService.finalAll().spliterator(), false)
								.collect(Collectors.toList());
		return users;
	}
}
