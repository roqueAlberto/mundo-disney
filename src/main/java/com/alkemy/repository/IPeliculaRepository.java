package com.alkemy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.alkemy.models.Pelicula;

@Repository("peliculaRepository")
public interface IPeliculaRepository extends JpaRepository<Pelicula, Integer> {

	@Query(value = "FROM Pelicula p WHERE p.titulo LIKE %:titulo%")
	public List<Pelicula> findyByTitulo(@Param("titulo") String titulo);
	
	@Query(value = "FROM Pelicula p WHERE p.genero.id = ?1")
	public List<Pelicula> findPeliculaByGenero(int genero);
	
	@Query(value = "FROM Pelicula p ORDER BY p.fechaCreacion ASC")
	public List<Pelicula> orderByFechaAsc();
	
	@Query(value = "FROM Pelicula p ORDER BY p.fechaCreacion DESC")
	public List<Pelicula> orderByFechaDesc();

}
