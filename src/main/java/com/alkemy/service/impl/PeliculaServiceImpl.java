package com.alkemy.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alkemy.models.Pelicula;
import com.alkemy.repository.IPeliculaRepository;
import com.alkemy.service.IPeliculaService;

@Service
public class PeliculaServiceImpl implements IPeliculaService {

	@Autowired
	IPeliculaRepository movieRepository;

	@Override
	public List<Pelicula> listar() {
		return movieRepository.findAll();
	}

	@Override
	public Optional<Pelicula> findyPeliculaById(int id) {
		return movieRepository.findById(id);
	}

	@Override
	public Pelicula add(Pelicula pelicula) {
		return movieRepository.save(pelicula);
	}

	@Override
	public Pelicula update(Pelicula pelicula) {
		return movieRepository.save(pelicula);
	}

	@Override
	public boolean delete(int id) {

		Optional<Pelicula> pelicula = findyPeliculaById(id);

		if (pelicula.get() != null) {
			movieRepository.delete(pelicula.get());
			return true;
		} else {
			return false;
		}
	}

	
	@Override
	public List<Pelicula> findyPeliculaByTitulo(String titulo) {	
		 return movieRepository.findyByTitulo(titulo);			
	}

	@Override
	public List<Pelicula> findyPeliculaByGenero(int idGenero) {	
		return movieRepository.findPeliculaByGenero(idGenero);	
	}

	
	@Override
	public List<Pelicula> orderByFecha(String orden) {
		
		if(orden.equalsIgnoreCase("ASC")) 
			return movieRepository.orderByFechaAsc();		
		else 		
		   return movieRepository.orderByFechaDesc();		
	}

}
