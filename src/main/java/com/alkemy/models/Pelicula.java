package com.alkemy.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data @NoArgsConstructor
@Entity
@Table(name = "pelicula")
public class Pelicula {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pelicula")
	private int id;
	
	private String imagen;
	
	private String titulo;
	
	@DateTimeFormat(iso = ISO.DATE)
	@Column(name = "fecha_creacion")
	private LocalDate fechaCreacion;
	
	private int calificacion;
	
	@ManyToOne
	@JoinColumn(name = "id_genero")
	private Genero genero;
	
	@JsonIgnoreProperties("peliculas")
	@JoinTable(name = "participacion",
			joinColumns = {@JoinColumn(name="id_pelicula")},
			inverseJoinColumns = {@JoinColumn(name="id_personaje")})
	@ManyToMany(fetch = FetchType.LAZY)
	private List<Personaje> personajes = new ArrayList<>();
	

	
	
	
	
	
}
