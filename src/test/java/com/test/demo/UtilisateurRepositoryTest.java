package com.test.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.test.demo.model.Utilisateurs;
import com.test.demo.repository.UtilisateursRepository;





@RunWith(SpringRunner.class)
@SpringBootTest
public class UtilisateurRepositoryTest {


	@Autowired
	UtilisateursRepository repository;


	Date date = new Date();
	
	  @Test 
	  public void testCreateUser() {
	  Utilisateurs utilisateur = Utilisateurs.builder()
				.nom("Badji")
				.genre("Masculin")
				.dateNaissance(date)
				.paysResidence("Senegal")
				.telephone("778955656")
				.build();
	  Utilisateurs saveDb = repository.save(utilisateur);
	  assertThat(utilisateur).hasFieldOrPropertyWithValue("nom", "Badji");
	  assertThat(utilisateur).hasFieldOrPropertyWithValue("paysResidence","Senegal"); 
	  assertThat(utilisateur).hasFieldOrPropertyWithValue("genre","Masculin"); 
	  }
	  
	  @Test 
	  public void testUpdateUser() { 
		  Utilisateurs utilisateur = Utilisateurs.builder()
					.nom("Badji")
					.genre("Masculin")
					.dateNaissance(date)
					.paysResidence("Senegal")
					.telephone("778955656")
					.build();
		  Utilisateurs saveDb = repository.save(utilisateur);
		  Utilisateurs getFromDb = saveDb;
		  getFromDb.setNom("Diatta");
		  assertNotNull(saveDb);
		  assertNotNull(saveDb.getId());
		  assertEquals(getFromDb.getNom(), saveDb.getNom());
		  assertEquals(utilisateur.getTelephone(), saveDb.getTelephone());
		  assertEquals(utilisateur.getGenre(), saveDb.getGenre());
	  
	  }
	  
	  
	  @Test 
	  public void testFindUserById() { 
		  Utilisateurs user1 = Utilisateurs.builder()
					.nom("Badji")
					.genre("Masculin")
					.dateNaissance(date)
					.paysResidence("Senegal")
					.telephone("778955656")
					.build();
			Utilisateurs user2 = Utilisateurs.builder()
					.nom("Badji")
					.genre("Masculin")
					.dateNaissance(date)
					.paysResidence("Senegal")
					.telephone("778955656")
					.build();
			Utilisateurs saveUser1 = repository.save(user1);
			Utilisateurs saveUser2 = repository.save(user2);
	  Utilisateurs userFound = repository.findById(saveUser2.getId()).get();
	  assertThat(userFound).isEqualTo(user2); 
	  
	  }
	  
	  @Test 
	  public void testListUser() { 
		  Utilisateurs user1 = Utilisateurs.builder()
					.nom("Badji")
					.genre("Masculin")
					.dateNaissance(date)
					.paysResidence("Senegal")
					.telephone("778955656")
					.build();
			Utilisateurs user2 = Utilisateurs.builder()
					.nom("Diata")
					.genre("Masculin")
					.dateNaissance(date)
					.paysResidence("Senegal")
					.telephone("771232323")
					.build();
			repository.save(user1);
			 repository.save(user2);
	  
	  List<Utilisateurs> allUsers = repository.findAll();
	  List<Utilisateurs> newUsers = new ArrayList<>();
	 allUsers.stream().map(user -> newUsers.add(user)).collect(Collectors.toList());
	  assertThat(newUsers.size()).isEqualTo(2); 
	  }
	  
	  @Test 
	  public void testRemoveUser() { 
		  Utilisateurs user1 = Utilisateurs.builder()
					.nom("Badji")
					.genre("Masculin")
					.dateNaissance(date)
					.paysResidence("Senegal")
					.telephone("778955656")
					.build();
			Utilisateurs user2 = Utilisateurs.builder()
					.nom("Diata")
					.genre("Masculin")
					.dateNaissance(date)
					.paysResidence("Senegal")
					.telephone("771232323")
					.build();
			Utilisateurs saveUser1 = repository.save(user1);
			 repository.save(user2);
		  
			 repository.delete(saveUser1);
		  
		  List<Utilisateurs> allUsers = repository.findAll();
		  List<Utilisateurs> users = new ArrayList<>();
		  allUsers.stream().map(user -> users.add(user)).collect(Collectors.toList());
	  
		  assertThat(users.size()).isEqualTo(1); 
	  }

	 
}