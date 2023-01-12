package com.test.demo.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.demo.dto.UtilisateursDto;
import com.test.demo.exceptions.ResourceNotFoundException;
import com.test.demo.service.UtilisateurService;


@RestController
@RequestMapping(path = "/api")
public class UtilisateursController {

	@Autowired
	UtilisateurService utilisateurService;

	/**
	 * Methode : save user
	 * @param user
	 * @return user
	 */
	 @PostMapping("/users")
	    public UtilisateursDto saveUser(@RequestBody UtilisateursDto user){
	        return utilisateurService.saveUser(user);
	    }
	 @GetMapping("/users")
	 public List<UtilisateursDto> users(){
	        return utilisateurService.listUsers();
	 }
	 /**
	  * Methode : update user by id
	  * @param userId
	  * @param user
	  * @return user
	  */
	 @PutMapping("/users/{id}")
	    public UtilisateursDto updateUser(@PathVariable Long id, @RequestBody UtilisateursDto userDTO){
		 userDTO.setId(id);
	        return utilisateurService.updateUser(userDTO);
	    }
	/**
	 * Methode : get user by id
	 * @param userId
	 * @return user
	 * @throws ResourceNotFoundException
	 */
	@GetMapping("/users/{id}")
    public UtilisateursDto getUser(@PathVariable(name = "id") Long userId) throws ResourceNotFoundException {
        return utilisateurService.getUserById(userId);
    }
}
