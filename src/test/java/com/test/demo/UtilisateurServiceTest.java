package com.test.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.h2.api.ErrorCode;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.ExpectedCount;

import com.test.demo.dto.UtilisateursDto;
import com.test.demo.exceptions.InvalideResourceException;
import com.test.demo.mapper.UtilisateurMapper;
import com.test.demo.model.Utilisateurs;
import com.test.demo.service.UtilisateurService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UtilisateurServiceTest {

	@Autowired
	private UtilisateurService utilisateurService;
	
	@Autowired
	private UtilisateurMapper dtoMapper;
	
//	@MockBean
//	private UtilisateursRepository UtilisateursRepository;
	
	Date date = new Date();
	@Test
	public void testCreateUser() {
		UtilisateursDto dto = UtilisateursDto.builder()
				.nom("Badji")
				.genre("Masculin")
				.dateNaissance(date)
				.paysResidence("Senegal")
				.telephone("778955656")
				.build();
		UtilisateursDto save = utilisateurService.saveUser(dto);
		UtilisateursDto dtoUpdate = save;
		dtoUpdate.setNom("Diatta");
		assertNotNull(save);
		assertNotNull(save.getId());
		assertEquals(dtoUpdate.getNom(), save.getNom());
		assertEquals(dto.getTelephone(), save.getTelephone());
		assertEquals(dto.getGenre(), save.getGenre());
	}
	@Test
	public void testFindUserById() {
		UtilisateursDto user1 = UtilisateursDto.builder()
				.nom("Badji")
				.genre("Masculin")
				.dateNaissance(date)
				.paysResidence("Senegal")
				.telephone("778955656")
				.build();
		UtilisateursDto user2 = UtilisateursDto.builder()
				.nom("Badji")
				.genre("Masculin")
				.dateNaissance(date)
				.paysResidence("Senegal")
				.telephone("778955656")
				.build();
		UtilisateursDto saveUser1 = utilisateurService.saveUser(user1);
		UtilisateursDto saveUser2 = utilisateurService.saveUser(user2);
		
		UtilisateursDto userFound = utilisateurService.getUserById(saveUser2.getId());
		assertThat(userFound).isEqualTo(user2); 
	}
	
	

	    @Test
	    public void testDelete() {
	    	UtilisateursDto user1 = UtilisateursDto.builder()
					.nom("Badji")
					.genre("Masculin")
					.dateNaissance(date)
					.paysResidence("Senegal")
					.telephone("778955656")
					.build();
			UtilisateursDto user2 = UtilisateursDto.builder()
					.nom("Diata")
					.genre("Masculin")
					.dateNaissance(date)
					.paysResidence("Senegal")
					.telephone("771232323")
					.build();
			UtilisateursDto saveUser1 = utilisateurService.saveUser(user1);
			utilisateurService.saveUser(user2);
			 utilisateurService.deleteUser(saveUser1.getId());
			 
	        List<UtilisateursDto> allUsers = utilisateurService.listUsers();
			  List<UtilisateursDto> users = new ArrayList<>();
			  allUsers.stream().map(user -> users.add(user)).collect(Collectors.toList());
	       
	        assertThat(users.size()).isEqualTo(1); 
	    }



}
