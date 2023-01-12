package com.test.demo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.test.demo.model.Utilisateurs;
import com.test.demo.repository.UtilisateursRepository;




@RunWith(SpringRunner.class)
@DataJpaTest
public class JPAUnitTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	UtilisateursRepository repository;

//	@Autowired
//	UtilisateurService service;

	Date localDate = new Date();
	
	
	  @Test 
	  public void should_save_a_user_by_repository() { 
	  Utilisateurs utilisateur = new Utilisateurs(0, "Badji", localDate, "French","775273025", "Masculin"); 
	  Utilisateurs saveDb = entityManager.persist(utilisateur);
	  Utilisateurs getFromDb = repository.getById(saveDb.getId());
	  assertThat(getFromDb).isEqualTo(saveDb);
	  assertThat(utilisateur).hasFieldOrPropertyWithValue("nom", "Badji");
	  assertThat(utilisateur).hasFieldOrPropertyWithValue("paysResidence","French"); 
	  assertThat(utilisateur).hasFieldOrPropertyWithValue("genre","Masculin"); 
	  }
	  
	  @Test 
	  public void should_update_a_user_by_repository() { 
	  Utilisateurs utilisateur = new Utilisateurs(0, "Badji", localDate, "French","775273025", "Masculin"); 
	  Utilisateurs saveDb = entityManager.persist(utilisateur);
	  Utilisateurs getFromDb = repository.getById(saveDb.getId());
	  
	  getFromDb.setNom("Diatta");
	  entityManager.persist(getFromDb);
	  
	  assertThat(getFromDb).isEqualTo(saveDb);
	  assertThat(utilisateur).hasFieldOrPropertyWithValue("nom", "Diatta");
	  assertThat(utilisateur).hasFieldOrPropertyWithValue("paysResidence","French"); 
	  assertThat(utilisateur).hasFieldOrPropertyWithValue("genre","Masculin"); 
	  }
	  
	  
	  @Test 
	  public void should_find_user_by_id_by_entity_manager() { 
		  Utilisateurs user1 = new Utilisateurs(0, "Khalifa", localDate, "French", "775273025",
		  "Masculin"); 
		  entityManager.persist(user1);
	  
	  Utilisateurs user2 = new Utilisateurs(0, "Anta", localDate, "French","779477258", "Feminin"); 
	  entityManager.persist(user2);
	  
	  Utilisateurs foundUser = repository.findById(user2.getId()).get();
	  
	  assertThat(foundUser).isEqualTo(user2); 
	  }
	  
	  @Test 
	  public void should_find_all_user_by_entity_manager() { 
		  Utilisateurs user1 = new Utilisateurs(0, "Khalifa", localDate, "French", "775273025",
		  "Masculin"); 
		  entityManager.persist(user1);
	  
	  Utilisateurs user2 = new Utilisateurs(0, "Anta", localDate, "French","779477258", "Feminin"); 
	  entityManager.persist(user2);
	  
	  Iterable<Utilisateurs> allUsers = repository.findAll();
	  List<Utilisateurs> userList = new ArrayList<>();
	  ((Collection<Utilisateurs>) allUsers).stream().map(user -> userList.add(user)).collect(Collectors.toList());
	  
	  assertThat(userList.size()).isEqualTo(2); 
	  }
	  
	  @Test 
	  public void should_remove_user_by_entity_manager() { 
		  Utilisateurs user1 = new Utilisateurs(0, "Khalifa", localDate, "French", "775273025","Masculin"); 
		  Utilisateurs userPersist = entityManager.persist(user1);
	  
		  Utilisateurs user2 = new Utilisateurs(0, "Anta", localDate, "French","779477258", "Feminin"); 
		  entityManager.persist(user2);
		  
		  entityManager.remove(userPersist);
		  
		  Iterable<Utilisateurs> allUsers = repository.findAll();
		  List<Utilisateurs> userList = new ArrayList<>();
		  ((Collection<Utilisateurs>) allUsers).stream().map(user -> userList.add(user)).collect(Collectors.toList());
	  
		  assertThat(userList.size()).isEqualTo(1); 
	  }
//	  
//	  
//	  @Test 
//	  public void should_store_a_user_by_service() { 
//		  UtilisateursDto utilisateur = new UtilisateursDto(0, "Khalifa", localDate, "French", "775273025",
//	  "Masculin"); 
//	   service.creerUser(utilisateur);
//	  
//	  assertThat(utilisateur).hasFieldOrPropertyWithValue("nom", "Khalifa");
//	  assertThat(utilisateur).hasFieldOrPropertyWithValue("paysResidence",
//	  "French"); assertThat(utilisateur).hasFieldOrPropertyWithValue("genre",
//	  "Masculin"); 
//	  }
	 
}