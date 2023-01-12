package com.test.demo.service;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.demo.aspect.LogEntryExit;
import com.test.demo.dto.UtilisateursDto;
import com.test.demo.exceptions.ResourceNotFoundException;
import com.test.demo.mapper.UtilisateurMapper;
import com.test.demo.model.Utilisateurs;
import com.test.demo.repository.UtilisateursRepository;



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
	public void creerUser(UtilisateursDto user) {
		Utilisateurs utilisateur = modelMapper.map(user, Utilisateurs.class);
		utilisateursRepository.save(utilisateur);
	}

	
	@LogEntryExit(showArgs = true, showResult = true, unit = ChronoUnit.MILLIS)
	 @Override
	    public UtilisateursDto getUserById(Long userId) throws ResourceNotFoundException {
		 Utilisateurs user = utilisateursRepository.findById(userId)
	                .orElseThrow(() -> new ResourceNotFoundException("User Not found"));
	        return dtoMapper.fromUser(user);
	    }
	
	@LogEntryExit(showArgs = true, showResult = true, unit = ChronoUnit.MILLIS)
	@Override
	public UtilisateursDto saveUser(UtilisateursDto user) {
		Utilisateurs utilisateur = modelMapper.map(user, Utilisateurs.class);
		return modelMapper.map(utilisateursRepository.save(utilisateur), UtilisateursDto.class);
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
        //log.info("Saving new Customer");
		Utilisateurs user=dtoMapper.fromUserDTO(userDTO);
		Utilisateurs savedCustomer = utilisateursRepository.save(user);
        return dtoMapper.fromUser(savedCustomer);
    }
	
}