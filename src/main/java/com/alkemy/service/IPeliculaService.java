package com.alkemy.service;

import java.util.List;
import java.util.Optional;

import com.alkemy.models.Pelicula;

public interface IPeliculaService {

	public List<Pelicula> listar();
	public Optional<Pelicula> findyPeliculaById(int id);
	public Pelicula add(Pelicula pelicula);
	public Pelicula update(Pelicula pelicula);
	public boolean delete(int id);
	public List<Pelicula> findyPeliculaByTitulo(String titulo);
	public List<Pelicula> findyPeliculaByGenero(int idGenero);
	public List<Pelicula> orderByFecha(String orden);
	
}
