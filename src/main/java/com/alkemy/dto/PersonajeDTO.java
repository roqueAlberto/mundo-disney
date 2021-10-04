package com.alkemy.dto;

import com.alkemy.models.Personaje;

import lombok.Data;

@Data
public class PersonajeDTO {

	
	public String imagen;
	public String nombre;
	
	
	public PersonajeDTO(Personaje personaje) {
		this.imagen = personaje.getImagen();
		this.nombre = personaje.getNombre();
	}


	
	
	
}
