package com.test.demo.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Builder
@JsonInclude
@JsonIgnoreProperties(ignoreUnknown = true)
@Data 
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class UtilisateursDto {

	private long id;
	@NotNull(message = "First Name cannot be null")
	private String nom;
	@NotNull(message = "First Name cannot be null")
	@JsonProperty(access = Access.AUTO)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date dateNaissance;
	@NotNull(message = "First Name cannot be null")
	private String paysResidence;
	@NotNull(message = "First Name cannot be null")
	private String telephone;
	@NotNull(message = "First Name cannot be null")
	private String genre;
	
	
	
	
	
	
	
	
}
