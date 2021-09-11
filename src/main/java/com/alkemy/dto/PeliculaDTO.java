package com.alkemy.dto;


import java.time.LocalDate;

import com.alkemy.models.Pelicula;

public class PeliculaDTO {

	private String imagen;
	private String titulo;
	private LocalDate fechaCreacion;
	
	
	public PeliculaDTO(Pelicula pelicula) {
		this.imagen = pelicula.getImagen();
		this.titulo = pelicula.getTitulo();
		this.fechaCreacion = pelicula.getFechaCreacion();
	}
	
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public LocalDate getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(LocalDate fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	
	
	
}
