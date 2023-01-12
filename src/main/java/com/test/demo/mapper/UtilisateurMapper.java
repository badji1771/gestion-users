package com.test.demo.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.test.demo.dto.UtilisateursDto;
import com.test.demo.model.Utilisateurs;
@Service
public class UtilisateurMapper {

	public UtilisateursDto fromUser(Utilisateurs user){
		UtilisateursDto userDTO=new UtilisateursDto();
        BeanUtils.copyProperties(user,userDTO);
        return  userDTO;
    }
	 public Utilisateurs fromUserDTO(UtilisateursDto userDTO){
		 Utilisateurs user=new Utilisateurs();
	        BeanUtils.copyProperties(userDTO,user);
	        return  user;
	    }
	
}
