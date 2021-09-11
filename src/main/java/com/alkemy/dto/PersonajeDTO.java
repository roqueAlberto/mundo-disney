package com.alkemy.dto;

import com.alkemy.models.Personaje;

public class PersonajeDTO {

	
	public String imagen;
	public String nombre;
	
	
	public PersonajeDTO(Personaje personaje) {
		this.imagen = personaje.getImagen();
		this.nombre = personaje.getNombre();
	}


	public String getImagen() {
		return imagen;
	}


	public void setImagen(String imagen) {
		this.imagen = imagen;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	
	
}
