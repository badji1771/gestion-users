package com.test.demo.service;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.demo.aspect.LogEntryExit;
import com.test.demo.dto.UtilisateursDto;
import com.test.demo.exceptions.ErrorCodes;
import com.test.demo.exceptions.InvalideResourceException;
import com.test.demo.exceptions.ResourceNotFoundException;
import com.test.demo.mapper.UtilisateurMapper;
import com.test.demo.model.Utilisateurs;
import com.test.demo.repository.UtilisateursRepository;
import com.test.demo.validator.UtilisateurValidator;



@Service
@Transactional(rollbackFor = {Throwable.class})
public class UtilisateursServiceImpl implements UtilisateurService{

	@Autowired
	UtilisateursRepository utilisateursRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private UtilisateurMapper dtoMapper;



	
	@LogEntryExit(showArgs = true, showResult = true, unit = ChronoUnit.MILLIS)
	 @Override
	    public UtilisateursDto getUserById(Long userId) throws ResourceNotFoundException {
		 Utilisateurs user = utilisateursRepository.findById(userId)
	                .orElseThrow(() -> new ResourceNotFoundException("l'utilisateur dont l'id = " + userId +" n'existe pas " ,ErrorCodes.USER_NOT_FOUND));
	        return dtoMapper.fromUser(user);
	    }
	
	@LogEntryExit(showArgs = true, showResult = true, unit = ChronoUnit.MILLIS)
	@Override
	public UtilisateursDto saveUser(UtilisateursDto user) {
		List<String> errors = UtilisateurValidator.validate(user);
		if (!errors.isEmpty()) {
			throw new InvalideResourceException("L\'utilisateur n\'est pas valide",ErrorCodes.USER_NOT_FOUND,errors);
		}
		//Utilisateurs utilisateur = modelMapper.map(user, Utilisateurs.class);
		return modelMapper.map(utilisateursRepository.save(modelMapper.map(user, Utilisateurs.class)), UtilisateursDto.class);
	}
	@LogEntryExit(showArgs = true, showResult = true, unit = ChronoUnit.MILLIS)
	@Override
    public List<UtilisateursDto> listUsers() {
        List<Utilisateurs> users = utilisateursRepository.findAll();
        List<UtilisateursDto> userDTOS = users.stream().map(user -> dtoMapper.fromUser(user)).collect(Collectors.toList());
        return userDTOS;
    }
	
	@LogEntryExit(showArgs = true, showResult = true, unit = ChronoUnit.MILLIS)
	@Override
    public UtilisateursDto updateUser(UtilisateursDto userDTO) {
		List<String> errors = UtilisateurValidator.validate(userDTO);
		if (!errors.isEmpty()) {
			throw new InvalideResourceException("L\'utilisateur n\'est pas valide",ErrorCodes.USER_NOT_FOUND,errors);
		}
		Utilisateurs userFound = utilisateursRepository.findById(userDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("l'utilisateur dont l'id = " + userDTO.getId() +" n'existe pas " ,ErrorCodes.USER_NOT_FOUND));
        return dtoMapper.fromUser(utilisateursRepository.save(dtoMapper.fromUserDTO(userDTO)));
    }
	
}