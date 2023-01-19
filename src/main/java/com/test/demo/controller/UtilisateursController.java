package com.test.demo.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.demo.dto.UtilisateursDto;
import com.test.demo.exceptions.ResourceNotFoundException;
import com.test.demo.service.UtilisateurService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RequestMapping(path = "/api")
public class UtilisateursController {

	@Autowired
	UtilisateurService utilisateurService;

	
	 @PostMapping("/users")
	 @ApiOperation(value = "Enregistre un utilisateur", notes = "Cette methode permet d'enregistrer un utulisateur",response = UtilisateursDto.class)
	 @ApiResponses(value = {
	 			@ApiResponse(code = 200, message = "Enregistrement effectué avec succes"),
	 			@ApiResponse(code = 400, message = "L'utilisateur fourni n'est pas valide")
	 		}
	 )
	    public UtilisateursDto saveUser(@RequestBody UtilisateursDto user){
	        return utilisateurService.saveUser(user);
	    }
	 @GetMapping("/users")
	 @ApiOperation(value = "Renvoi la liste des utilisateurs", notes = "Cette methode permet de renvoyer la liste des utilisateurs qui existe dans la base de donnée",responseContainer = "List<UtilisateurDto>")
	 @ApiResponses(value = {
	 			@ApiResponse(code = 200, message = "la liste des utilisateur / une liste vide")
	 			
	 		}
	 )
	 public List<UtilisateursDto> users(){
	        return utilisateurService.listUsers();
	 }
	
	 @PutMapping("/users/{id}")
	 @ApiOperation(value = "Modifier un utilisateur", notes = "Cette methode permet de modifier un utilisateur",response = UtilisateursDto.class)
	 @ApiResponses(value = {
	 			@ApiResponse(code = 200, message = "Enregistrement effectué avec succes"),
	 			@ApiResponse(code = 400, message = "L'utilisateur fourni n'est pas valide")
	 		}
	 )
	    public UtilisateursDto updateUser(@PathVariable Long id, @RequestBody UtilisateursDto userDTO){
		 userDTO.setId(id);
	        return utilisateurService.updateUser(userDTO);
	    }
	
	 
	@GetMapping("/users/{id}")
	@ApiOperation(value = "Rechercher un utilisateur", notes = "Cette methode permet de rechercher un utulisateur",response = UtilisateursDto.class)
	@ApiResponses(value = {
				@ApiResponse(code = 200, message = "l'utilisateur a ete trouvé avec succes"),
				@ApiResponse(code = 404, message = "Aucun n'utilisateur n'existe dans la base de donnée avec l'id fourni")
	}
	)
    public UtilisateursDto getUser(@PathVariable(name = "id") Long userId) throws ResourceNotFoundException {
        return utilisateurService.getUserById(userId);
    }
	
	@DeleteMapping("/users/{id}")
	@ApiOperation(value = "Supprimer un utilisateur", notes = "Cette methode permet de supprimer un utilisateur")
	@ApiResponses(value = {
				@ApiResponse(code = 200, message = "l'utilisateur a ete supprimer avec succés"),
				@ApiResponse(code = 404, message = "Aucun n'utilisateur n'existe dans la base de donnée avec l'id fourni")
	}
	)
    public void deleteUser(@PathVariable(name = "id") Long id){
		utilisateurService.deleteUser(id);
    }
}
