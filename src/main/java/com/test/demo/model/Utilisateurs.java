package com.test.demo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@Entity
@Table(name = "utilisateurs")
public class Utilisateurs {

	

	

	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "UtilisateursIdGenerator")
	@GenericGenerator(name = "UtilisateursIdGenerator", strategy = "native")
	@Column(name = "ID", nullable = false, insertable = true, updatable = false)
	private long id;

	@Column(name = "NAME", nullable = false)
	@NotBlank(message = "Name is mandatory")
	private String nom;

	@Column(name = "BIRTHDAY", nullable = true)
	@DateTimeFormat(iso = ISO.DATE)
	@JsonFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "Birthday is mandatory")
	@Past(message = "Birthday must be a date in the past")
	private Date dateNaissance;

	@Column(name = "RESIDENCE", nullable = false)
	@NotBlank(message = "Country of residence is mandatory")
	private String paysResidence;

	@Column(name = "CALL", nullable = true)
	private String telephone;

	@Column(name = "GENDER", nullable = true)
	private String genre;

	
	
	
}
