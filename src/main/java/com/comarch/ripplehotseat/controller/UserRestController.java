package com.comarch.ripplehotseat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comarch.ripplehotseat.dto.UserDTO;
import com.comarch.ripplehotseat.model.User;
import com.comarch.ripplehotseat.service.UserService;
import com.comarch.ripplehotseat.util.ObjectMapperUtils;

/**
 * 
 * @author Krzysztof Sajkowski
 *
 */
@RestController
@RequestMapping("/users")
public class UserRestController {

	@Autowired
	public UserService userService;
	
	@GetMapping(value="")
	public List<UserDTO> findAll() {
		return ObjectMapperUtils.mapAll(userService.findAll(), UserDTO.class);
	}
	
	@GetMapping(value = "/byId/{id}")
	public UserDTO findById(@PathVariable("id") String id) {
		return ObjectMapperUtils.map(userService.findById(id), UserDTO.class);
	}
	
	@GetMapping(value = "/byLogin/{login}")
	public UserDTO findByLogin(@PathVariable("login") String login) {
		return ObjectMapperUtils.map(userService.findByLogin(login), UserDTO.class);
	}
	
	@PostMapping(value = "/save")
	public ResponseEntity<String> save(@RequestBody UserDTO userDTO) {
		if(userDTO.getLogin() == null || userDTO.getPassword() == null) {
			return new ResponseEntity<String>("'login' and 'password' are required", HttpStatus.FORBIDDEN);
		}
		if(!userDTO.getLogin().endsWith("@comarch.com")) {
			return new ResponseEntity<String>("'login' must be a comarch email address (@comarch.com)", HttpStatus.FORBIDDEN);
		}
		if(userService.findByLogin(userDTO.getLogin()) != null) {
			return new ResponseEntity<String>("'login' must be unique", HttpStatus.FORBIDDEN);
		}
		userDTO.setId(null);
		userDTO.encryptPassword();
		userService.save(ObjectMapperUtils.map(userDTO, User.class));
		return new ResponseEntity<String>("User added successfully", HttpStatus.OK);
	}
	
	@PatchMapping(value = "/update/{id}")
	public ResponseEntity<String> update(@PathVariable("id") String id, @RequestBody UserDTO userDTO) {
		userDTO.setPassword(Integer.toString(userDTO.getPassword().hashCode()));
		if(userService.findById(id) == null) {
			return new ResponseEntity<String>("User could not be found", HttpStatus.NOT_FOUND);
		}
		if(userDTO.getLogin() == null || userDTO.getPassword() == null) {
			return new ResponseEntity<String>("'login' and 'password' are required", HttpStatus.FORBIDDEN);
		}
		if(!userDTO.getLogin().endsWith("@comarch.com")) {
			return new ResponseEntity<String>("'login' must be a comarch email address (@comarch.com)", HttpStatus.FORBIDDEN);
		}
		if(userService.findByLogin(userDTO.getLogin()) != null) {
			return new ResponseEntity<String>("'login' must be unique", HttpStatus.FORBIDDEN);
		}
		userDTO.setId(id);
		userDTO.encryptPassword();
		userService.save(ObjectMapperUtils.map(userDTO, User.class));
		return new ResponseEntity<String>("User updated successfully", HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<String> deleteById(@PathVariable("id") String id) {
		if(userService.findById(id) == null) {
			return new ResponseEntity<String>("User could not be found", HttpStatus.NOT_FOUND);
		}
		userService.deleteById(id);
		return new ResponseEntity<String>("User deleted successfully", HttpStatus.OK);
	}
	
}
