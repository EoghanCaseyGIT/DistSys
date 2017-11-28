package com.fourth.musicBackup.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fourth.musicBackup.model.User;
import com.fourth.musicBackup.repository.UserRepository;

import java.util.List;


@RestController
//@RequestMapping("/api")
public class UserController {
	
	@Autowired
	UserRepository userRepository;
	
	//Get all users
	@GetMapping("/users")
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	//create new user
	@PostMapping("/users")
	public User createUser(@Valid @RequestBody User user){
		return userRepository.save(user);
	}
	
	//get single user
	@GetMapping( "/users/{id}")
	public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long userId) {
		User user = userRepository.findOne(userId);
		if(user == null){
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(user);
	}
	
	//update User
	@PutMapping("/users/{id}")
	public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long userId, @Valid @RequestBody User userDetails){
		User user = userRepository.findOne(userId);
		if(user == null) {
			return ResponseEntity.notFound().build();
		}
		user.setUsername(userDetails.getUsername());
		user.setPassword(userDetails.getPassword());
		
		
		User updatedUser = userRepository.save(user);
		return ResponseEntity.ok(updatedUser);
	}
	
	//delete user
	
	@DeleteMapping("/users/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable(value = "id") Long userId){
		User user = userRepository.findOne(userId);
		if(user == null){
			return ResponseEntity.notFound().build();
			
		}
		
		userRepository.delete(user);
		return ResponseEntity.ok().build();
	}
	
	
}
