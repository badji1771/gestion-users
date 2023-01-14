package com.test.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.test.demo.dto.UtilisateursDto;
import com.test.demo.mapper.UtilisateurMapper;
import com.test.demo.model.Utilisateurs;
import com.test.demo.repository.UtilisateursRepository;
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
	
	Date localDate = new Date();
	@Test
	public void testCreateUser() {
		UtilisateursDto dto = UtilisateursDto.builder()
				.nom("Badji")
				.genre("Masculin")
				.dateNaissance(new Date())
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
//	@Test
//	public void testGetUserById() {
//		 Utilisateurs utilisateur = new Utilisateurs(0, "Badji", localDate, "French","775273025", "Masculin"); 
//		 Mockito.when(UtilisateursRepository.findById(1L)).thenReturn(utilisateur);
//		 assertThat(utilisateurService.saveUser(dtoMapper.fromUser(utilisateur))).isEqualTo(dtoMapper.fromUser(utilisateur));
//	}
//	@Test
//	public void testUpdateUserById() {
//		 Utilisateurs utilisateur = new Utilisateurs(0, "Badji", localDate, "French","775273025", "Masculin"); 
//		 Mockito.when(UtilisateursRepository.findById(1L)).thenReturn(utilisateur);
//		 utilisateur.setNom("Diatta");
//		 Mockito.when(UtilisateursRepository.save(utilisateur)).thenReturn(utilisateur);
//		 assertThat(utilisateurService.updateUser(dtoMapper.fromUser(utilisateur))).isEqualTo(dtoMapper.fromUser(utilisateur));
//	}
}
