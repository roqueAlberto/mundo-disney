package com.alkemy.service;

import java.util.List;
import java.util.Optional;

import com.alkemy.models.Personaje;

public interface IPersonajeService {

	public List<Personaje> listarPersonajes();
	public Personaje add(Personaje personaje);
	public Personaje update(Personaje personaje);
	public boolean delete(int id);
	public Optional<Personaje> findPersonajeById(int id);
	public List<Personaje> findPersonajeByName(String nombre);
	public List<Personaje> findPersonajeByAge(int edad);
	public List<Personaje> findPersonajeByMovie(int movie);

}
