package com.test.demo;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.demo.controller.UtilisateursController;
import com.test.demo.dto.UtilisateursDto;
import com.test.demo.service.UtilisateurService;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@WebMvcTest(UtilisateursController.class)
public class UserControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private UtilisateurService utilisateurService;
	

	
	@Test
 public void createUser()throws Exception{
	 ObjectMapper objectMapper = new ObjectMapper();
	 UtilisateursDto dto = UtilisateursDto.builder()
			 .id(2L)
			.nom("Diatta")
			.genre("Masculin")
			.dateNaissance(new Date())
			.paysResidence("Senegal")
			.telephone("771232322")
			.build();
	 
	 mvc.perform(post("/api/users").contentType(MediaType.APPLICATION_JSON)
			 .content(objectMapper.writeValueAsString(dto)))
	 .andExpect(status().isOk())
	 .andDo(print());
 }
	@Test
	public void findUser() throws Exception{
		Long id = 1L;
		UtilisateursDto dto = UtilisateursDto.builder()
			.id(id)
			.nom("Diatta")
			.genre("Masculin")
			.dateNaissance(new Date())
			.paysResidence("Senegal")
			.telephone("771232322")
			.build();
	 when(utilisateurService.getUserById(id)).thenReturn((dto));
	 mvc.perform(get("/api/users/{id}",id)).andExpect(status().isOk())
	 .andExpect(jsonPath("$.id").value(id))
	 .andExpect(jsonPath("$.nom").value("Diatta"))
	 .andExpect(jsonPath("$.paysResidence").value("Senegal"))
	 .andDo(print());
		
	}
	
	@Test
	public void getListUser() throws Exception{
		List<UtilisateursDto> users = Stream.of(
				new UtilisateursDto(1L,"Badji",new Date(),"Senegal","778955656","M"),
				new UtilisateursDto(2L,"Gaye",new Date(),"Senegal","778955656","M"),
				new UtilisateursDto(3L,"Thiam",new Date(),"Senegal","778955656","M")
				).collect(Collectors.toList());
		when(utilisateurService.listUsers()).thenReturn(users);
		mvc.perform(get("/api/users"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.size()").value(users.size()))
		.andDo(print());
	}
	
	@Test
	public void updateUser() throws Exception{
		ObjectMapper objectMapper = new ObjectMapper();
		Long id = 2L;
		UtilisateursDto dto = UtilisateursDto.builder()
				.id(id)
				.nom("Diatta")
				.genre("Masculin")
				.dateNaissance(new Date())
				.paysResidence("Senegal")
				.telephone("771232322")
				.build();
		UtilisateursDto updateDto = UtilisateursDto.builder()
				.id(id)
				.nom("Badji")
				.genre("Masculin")
				.dateNaissance(new Date())
				.paysResidence("Mali")
				.telephone("771111122")
				.build();
		when(utilisateurService.getUserById(id)).thenReturn(dto);
		when(utilisateurService.saveUser(any(UtilisateursDto.class))).thenReturn(updateDto);
		mvc.perform(put("/api/users/{id}",id).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(updateDto)))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.nom").value(updateDto.getNom()))
		 .andExpect(jsonPath("$.paysResidence").value(updateDto.getPaysResidence()))
		 .andExpect(jsonPath("$.telephone").value(updateDto.getTelephone()))
		 .andDo(print());
	}
	@Test
	 public void deleteUser() throws Exception{
		 Long id = 1L;
		 UtilisateursDto dto = UtilisateursDto.builder()
					.id(id)
					.nom("Diatta")
					.genre("Masculin")
					.dateNaissance(new Date())
					.paysResidence("Senegal")
					.telephone("771232322")
					.build();
		 when(utilisateurService.deleteUser(id)).thenReturn(dto);
		 mvc.perform(delete("/api/users/{id}",id))
		 .andExpect(status().isOk())
		 .andDo(print());
	 }
}
