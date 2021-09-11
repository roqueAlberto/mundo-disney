package com.alkemy.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alkemy.models.Personaje;
import com.alkemy.repository.IPersonajeRepository;
import com.alkemy.service.IPersonajeService;

@Service
public class PersonajeServiceImpl implements IPersonajeService {

	@Autowired
	private IPersonajeRepository personajeRepository;
	
	@Override
	public List<Personaje> listarPersonajes() {	
		return personajeRepository.findAll();
	}

	
	@Override
	public Personaje add(Personaje personaje) {	
		return personajeRepository.save(personaje);		
	}
	
	
	@Override
	public Personaje update(Personaje personaje) {	
		return personajeRepository.save(personaje);	
	}


	@Override
	public boolean delete(int id) {
		
		if(findPersonajeById(id) != null) {
			personajeRepository.deleteById(id);	
			return true;
		}else {
			return false;
		}
	   	
	}


	@Override
	public Optional<Personaje> findPersonajeById(int id) {	
		 return personajeRepository.findById(id);	
	}


	@Override
	public List<Personaje> findPersonajeByName(String nombre) {	      
		return personajeRepository.findPersonajeByName(nombre);
	}


	@Override
	public List<Personaje> findPersonajeByAge(int edad) {	
		return personajeRepository.findPersonajeByAge(edad);		
	}


	@Override
	public List<Personaje> findPersonajeByMovie(int movie) {		
		return personajeRepository.findPersonajeByMovie(movie);
	}


	


}
