package com.alkemy.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.alkemy.models.Personaje;

@Repository("personajeRepository")
public interface IPersonajeRepository extends JpaRepository<Personaje, Integer> {

	
    @Query(value = "FROM Personaje p WHERE p.nombre LIKE %:nombre%")
	public List<Personaje> findPersonajeByName(@Param("nombre")String nombre);
	
	@Query(value = "FROM Personaje p WHERE p.edad = ?1")
	public List<Personaje> findPersonajeByAge(int edad);
	
	@Query(value = "SELECT p.personajes FROM Pelicula p WHERE p.id = ?1")
	public List<Personaje> findPersonajeByMovie(int idPelicula);
	
}
