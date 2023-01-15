package com.test.demo.service;

import java.util.List;

import com.test.demo.dto.UtilisateursDto;
import com.test.demo.exceptions.ResourceNotFoundException;

public interface UtilisateurService {
	

	UtilisateursDto saveUser(UtilisateursDto user);

	List<UtilisateursDto> listUsers();

	UtilisateursDto getUserById(Long userId) throws ResourceNotFoundException;

	UtilisateursDto updateUser(UtilisateursDto userDTO);
}
