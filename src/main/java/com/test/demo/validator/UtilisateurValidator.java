package com.test.demo.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.test.demo.dto.UtilisateursDto;



public class UtilisateurValidator {

	public static List<String> validate(UtilisateursDto dto){
		List<String> errors = new ArrayList<>();
		
		if (dto == null) {
			errors.add("Veuillez renseigner le nom de l'utilisateur");
			errors.add("Veuillez renseigner le telephone de l'utilisateur");
			errors.add("Veuillez renseigner le genre de l'utilisateur");
			errors.add("Veuillez renseigner le pays de l'utilisateur");
			errors.add("Veuillez renseigner la date de naissance de l'utilisateur");
			errors.add("Veuillez renseigner la date de naissance de l'utilisateur");
			return errors;
		}
		if (!StringUtils.hasLength(dto.getNom())) {
			errors.add("Veuillez renseigner le nom de l'utilisateur");
		}
		
		if (!StringUtils.hasLength(dto.getPaysResidence())) {
			errors.add("Veuillez renseigner le pays de residence de l'utilisateur");
		}
		
		return errors;
	}
	
}
