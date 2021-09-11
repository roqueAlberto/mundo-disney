package com.alkemy.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alkemy.dto.PeliculaDTO;
import com.alkemy.models.Pelicula;
import com.alkemy.service.IPeliculaService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/movies")

public class PeliculaController {

	@Autowired
	private IPeliculaService peliculaService;

	@GetMapping("/")
	@ApiOperation(value = "Listar peliculas con imagen, titulo y fecha de creacion", notes = "Servicio para mostrar los personajes de Disney")
	@ApiResponses(value = {@ApiResponse(code = 400, message = "Error en la solicitud"),
			               @ApiResponse(code = 200, message = "Personajes traidos correctamente") })
	public ResponseEntity<List<PeliculaDTO>> listaPelicula() {

		List<Pelicula> listaPeliculas = peliculaService.listar();
		List<PeliculaDTO> peliculasDTO = new ArrayList<>();

		for (Pelicula pelicula : listaPeliculas)
			peliculasDTO.add(new PeliculaDTO(pelicula));
								
		return ResponseEntity.ok(peliculasDTO);
	}
	
	
	@GetMapping
	@ApiOperation(value = "Listar peliculas segun el filtro deseado, por nombre, genero u orden", notes = "Servicio para mostrar las peliculas")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Error en la solicitud"),
			                @ApiResponse(code = 200, message = "Peliculas traidas correctamente") })
	public ResponseEntity<List<Pelicula>> listarPeliculas(
			@RequestParam(value = "name", required = false) String nombre,
			@RequestParam(value = "genre", required = false) String idGenero,
			@RequestParam(value = "order", required = false) String order) {
		
		List<Pelicula> peliculas = new ArrayList<>();

		if (nombre != null) {
			peliculas =  peliculaService.findyPeliculaByTitulo(nombre);
		} else if (idGenero != null) {
			peliculas =  peliculaService.findyPeliculaByGenero(Integer.parseInt(idGenero));	
		} else if(order != null) {			
			peliculas =  peliculaService.orderByFecha(order);			
		}
		
		return ResponseEntity.ok(peliculas);

	}

	
	@GetMapping("/{id}")
	@ApiOperation(value = "Buscar pelicula a traves de su id", notes = "Servicio para buscar pelicula por id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "La peticion fue recibida correctamente"),
			                @ApiResponse(code = 401, message = "No esta autorizado para realizar la peticion"),
			                @ApiResponse(code = 403, message = "El acceso está prohibido"),
			                @ApiResponse(code = 404, message = "No se puede encontrar la pagina solicitada") })	
	public ResponseEntity<Pelicula> getPelicula(@PathVariable int id) {
		Optional<Pelicula> pelicula = peliculaService.findyPeliculaById(id);
		return ResponseEntity.ok(pelicula.get());

	}

	
	@PostMapping("/agregar")
	@ApiOperation(value = "Agregar pelicula", notes = "Servicio para crear una nueva pelicula")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "La peticion fue recibida correctamente"),
			                @ApiResponse(code = 201, message = "Pelicula agregada correctamente"),
			                @ApiResponse(code = 401, message = "No esta autorizado para realizar la peticion"),
			                @ApiResponse(code = 403, message = "El acceso está prohibido"),
			                @ApiResponse(code = 404, message = "No se puede encontrar la pagina solicitada") })
	public ResponseEntity<Pelicula> agregar(@RequestBody Pelicula pelicula) {
		Pelicula p = peliculaService.add(pelicula);
		return ResponseEntity.ok(p);

	}

	
	@PutMapping("/actualizar")
	@ApiOperation(value = "Actualizar pelicula", notes = "Servicio para actualizar una pelicula")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "La peticion fue recibida correctamente"),
			                @ApiResponse(code = 201, message = "Peticion completa"),
			                @ApiResponse(code = 401, message = "No esta autorizado para realizar la peticion"),
			                @ApiResponse(code = 403, message = "El acceso está prohibido"),
			                @ApiResponse(code = 404, message = "No se puede encontrar la pagina solicitada") })
	public ResponseEntity<Pelicula> actualizar(@RequestBody Pelicula pelicula) {
		Pelicula p = peliculaService.update(pelicula);
		return ResponseEntity.ok(p);

	}

	
	
	@DeleteMapping("/eliminar/{id}")
	@ApiOperation(value = "Eliminar pelicula", notes = "Servicio para eliminar pelicula")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "La peticion fue recibida correctamente"),
			                @ApiResponse(code = 401, message = "Se requiere de un usuario y contraseña"),
			                @ApiResponse(code = 403, message = "No esta autorizado para realizar la peticion") })	
	public ResponseEntity<Pelicula> eliminar(@PathVariable int id) {

		if (peliculaService.delete(id))
			return new ResponseEntity<>(HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
