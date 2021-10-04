package com.alkemy.dto;


import java.time.LocalDate;

import com.alkemy.models.Pelicula;

import lombok.Data;

@Data
public class PeliculaDTO {

	private String imagen;
	private String titulo;
	private LocalDate fechaCreacion;
	
	
	public PeliculaDTO(Pelicula pelicula) {
		this.imagen = pelicula.getImagen();
		this.titulo = pelicula.getTitulo();
		this.fechaCreacion = pelicula.getFechaCreacion();
	}
	
	
	
	
	
}
