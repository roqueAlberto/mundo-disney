package com.alkemy.models;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data @NoArgsConstructor
@Entity
@Table(name = "personaje")
public class Personaje {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_personaje")
	private int id;
	private String imagen;
	private String nombre;
	private int edad;
	private double peso;
	private String historia;
		
	@JsonIgnoreProperties("personajes")
	@ManyToMany(mappedBy = "personajes", fetch = FetchType.LAZY)
	private List<Pelicula> peliculas = new ArrayList<>();
	

	
}
